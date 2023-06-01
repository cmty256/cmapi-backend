package com.chenmeng.cmapigateway;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.chenmeng.cmapicommon.model.entity.InterfaceInfo;
import com.chenmeng.cmapicommon.model.entity.User;
import com.chenmeng.cmapicommon.service.InnerInterfaceInfoService;
import com.chenmeng.cmapicommon.service.InnerUserInterfaceInfoService;
import com.chenmeng.cmapicommon.service.InnerUserService;
import com.chenmeng.sdk.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 自定义全局过滤器
 */
@Slf4j
// bean 容器
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @DubboReference
    private InnerUserService innerUserService;

    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;

    // 模拟接口的 IP 前缀 -- private static final -- 快捷键 prsf
    private static final String INTERFACE_HOST = "http://localhost:8123";
    // 允许通过的请求 IP 前缀
    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1","127.0.0.2");

    /**
     * 过滤器
     *
     * @param exchange 交换机
     * @param chain    链
     * @return {@link Mono}<{@link Void}> 一个不需要返回值的响应
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 请求日志
        ServerHttpRequest request = exchange.getRequest();
        // String path = INTERFACE_HOST + request.getPath().value();
        String path = request.getPath().value();
        String method = Objects.requireNonNull(request.getMethod()).toString();
        log.info("请求的唯一标识：" + request.getId());
        log.info("请求的路径：" + path);
        log.info("请求的方法：" + method);
        log.info("请求的参数：" + request.getQueryParams());
        String sourceAddress = Objects.requireNonNull(request.getLocalAddress()).getHostString();
        log.info("请求的来源地址：" + sourceAddress);
        // 地址后面带有端口号
        log.info("请求的来源地址：" + request.getRemoteAddress());
        // 2. 访问控制 - 黑白名单
        ServerHttpResponse response = exchange.getResponse();
        if (! IP_WHITE_LIST.contains(sourceAddress)) {
            // 响应无权限：被禁止的
            return handleNoAuth(response);
        }
        // 3. 用户鉴权(判断 ak、sk 是否合法)
        // 3.1 从请求头中获取参数
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        // 防止中文乱码，解码处理
        String body = URLUtil.decode(headers.getFirst("body"));
        log.info("请求的参数1：" + body);
        // 3.2 判断是否有空参
        // boolean hasBlank = StrUtil.hasBlank(accessKey, nonce, timestamp, sign, body);
        // if (hasBlank) {
        //     return handleInvokeError(response);
        // }
        // 3.3.1 使用 accessKey 去数据库查询 secretKey
        User invokeUser = null;
        try {
            invokeUser = innerUserService.getInvokeUser(accessKey);
        } catch (Exception e) {
            log.error("getInvokeUser error", e);
        }
        if (invokeUser == null) {
            return handleInvokeError(response);
        }
        String secretKey = invokeUser.getSecretKey();
        // 3.3.2 检查签名是否合法
        String sign1 = SignUtil.getSign(body, secretKey);
        if (!StrUtil.equals(sign, sign1)) {
            return handleInvokeError(response);
        }
        // todo 3.4 判断随机数 nonce
        // 3.5 判断时间戳是否为数字
        if (!NumberUtil.isNumber(timestamp)) {
            return handleInvokeError(response);
        }
        // 3.6 确保五分钟内的请求才有效
        final long FIVE_MINUTES = 5 * 60 * 1000L;
        if ((System.currentTimeMillis() - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
            return handleInvokeError(response);
        }
        // 4. 请求的模拟接口是否存在？
        InterfaceInfo invokeInterfaceInfo = null;
        try {
            invokeInterfaceInfo = innerInterfaceInfoService.getInterfaceInfo(path, method);
        } catch (Exception e) {
            log.error("getInvokeInterfaceInfo error", e);
        }
        if (invokeInterfaceInfo == null) {
            return handleInvokeError(response);
        }
        // todo 4.2 检查是否还有调用次数 -- 已解决
        if (!innerUserInterfaceInfoService.hasInvokeNum(invokeUser.getId(), invokeInterfaceInfo.getId())) {
            log.error("当前剩余调用次数为 0");
            return handleInvokeError(response);
        }
        // 5. 请求转发，调用模拟接口 + 响应日志
        return handleResponse(exchange, chain, invokeUser.getId(), invokeInterfaceInfo.getId());
    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**
     * 处理响应
     *
     * @param exchange
     * @param chain
     * @return
     */
    private Mono<Void> handleResponse(ServerWebExchange exchange, GatewayFilterChain chain, long userId, long interfaceInfoId) {
        try {
            // 从交换机拿到原始response
            ServerHttpResponse originalResponse = exchange.getResponse();
            // 缓冲区工厂 拿到缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            // 拿到状态码
            HttpStatus statusCode = originalResponse.getStatusCode();

            if (statusCode == HttpStatus.OK) {
                // 装饰，增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    // 等调用完转发的接口后才会执行
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        // 对象是响应式的
                        if (body instanceof Flux) {
                            // 我们拿到真正的body
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            // 往返回值里面写数据，给前端
                            // 拼接字符串
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // 7. 调用成功，接口调用次数+1
                                try {
                                    innerUserInterfaceInfoService.invokeInterfaceCount(userId, interfaceInfoId);
                                } catch (Exception e) {
                                    log.error("invokeInterfaceCount error", e);
                                }
                                // data从这个content中读取
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);// 释放掉内存
                                // 6.构建日志
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);// data
                                rspArgs.add(data);
                                log.info("<--- status:{} data:{}"// data
                                        , rspArgs.toArray());// log.info("<-- {} {}", originalResponse.getStatusCode(), data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            // 8.调用失败返回错误状态码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                // 设置 response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);// 降级处理返回数据
        } catch (Exception e) {
            log.error("网关响应处理出现异常：" + e);
            return chain.filter(exchange);
        }
    }

    /**
     * 处理任何身份验证
     *
     * @param response 响应
     * @return {@link Mono}<{@link Void}>
     */
    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        // 返回状态码：被禁止的
        response.setStatusCode(HttpStatus.FORBIDDEN);
        // setComplete() 方法用于标记响应已经完成，可以返回响应
        return response.setComplete();
    }

    /**
     * 处理调用错误
     *
     * @param response 响应
     * @return {@link Mono}<{@link Void}>
     */
    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        // 返回状态码：服务器内部错误
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}