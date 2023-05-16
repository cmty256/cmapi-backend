package com.chenmeng.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.chenmeng.sdk.model.User;
import com.chenmeng.sdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



/**
 * 模拟接口--服务端
 *
 * @author 沉梦
 */

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/{name}")
    public String getNameByGet(@PathVariable(value = "name") String name) {
        return "发送GET请求 你的名字是：" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam(value = "name") String name) {
        return "发送POST请求 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getNameByPostWithJson(@RequestBody User user, HttpServletRequest request) {
        String accessKey = request.getHeader("accessKey");
        // 防止中文乱码 -- 解码
        String body = URLUtil.decode(request.getHeader("body"));
        // 签名
        String sign = request.getHeader("sign");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");

        boolean hasBlank = StrUtil.hasBlank(accessKey, body, sign, nonce, timestamp);
        // 判断是否 为空
        if (hasBlank) {
            return "无权限";
        }
        // TODO 使用 accessKey 去数据库查询 secretKey
        // 假设查到的 secretKey 是 abc 进行加密得到签名 sign
        String secretKey = "abc";
        String sign1 = SignUtil.getSign(body, secretKey);
        // 判断签名是否相等
        if (!StrUtil.equals(sign, sign1)) {
            return "无权限";
        }
        // TODO 判断随机数 nonce
        // 时间戳是否为数字
        if (!NumberUtil.isNumber(timestamp)) {
            return "无权限";
        }
        // 5 分钟内的请求才有效
        if (System.currentTimeMillis() - Long.parseLong(timestamp) > 5 * 60 * 1000) {
            return "无权限";
        }
        return "发送POST请求 JSON中你的名字是：" + user.getName();
    }

}
