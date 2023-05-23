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
        return "发送POST请求 JSON中你的名字是：" + user.getName();
    }

}
