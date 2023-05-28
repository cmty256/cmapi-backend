package com.chenmeng.sdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.chenmeng.sdk.model.User;
import com.chenmeng.sdk.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口的客户端
 *
 * @author: 沉梦
 */
public class CmApiClient {

    // 网关 IP 前缀
    private static final String GATEWAY_HOST = "http://localhost:8090";

    private static final String INTERFACE_HOST = "http://localhost:8123";

    private final String accessKey;

    private final String secretKey;

    public CmApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }


    public String getNameByGet(String name) {
        //RESTful 风格
        return HttpUtil.get(GATEWAY_HOST + "/api/name/" + name);
    }

    public String getNameByPost(String name) {
        // 可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        return HttpUtil.post(GATEWAY_HOST + "/api/name/", paramMap);
    }

    public String getNameByPostWithJson(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .addHeaders(getHeaders(json))
                .body(json)
                .execute();
        System.out.println("response = " + response);
        System.out.println("status = " + response.getStatus());
        if (response.isOk()) {
            return response.body();
        }
        return "fail";
    }

    public String getByTest(User user) {
        if ("getNameByGet".equals(user.getInterfaceName())) {
            return getNameByGet(user.getName());
        }
        if ("getNameByPost".equals(user.getInterfaceName())) {
            return getNameByPost(user.getName());
        }
        if ("getNameByPostWithJson".equals(user.getInterfaceName())) {
            return getNameByPostWithJson(user);
        }
        return "测试调用失败";
    }

    private Map<String, String> getHeaders(String body) {
        HashMap<String, String> header = new HashMap<>();
        header.put("accessKey", accessKey);
        header.put("sign", SignUtil.getSign(body, secretKey));
        // 防止中文乱码 -- 转换
        header.put("body", URLUtil.encode(body));
        header.put("nonce", RandomUtil.randomNumbers(5));
        header.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return header;
    }
}









