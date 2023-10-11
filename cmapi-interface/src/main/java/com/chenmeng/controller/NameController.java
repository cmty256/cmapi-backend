package com.chenmeng.controller;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.chenmeng.sdk.model.RequestParams;
import org.apache.commons.lang3.StringUtils;
import com.chenmeng.sdk.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



/**
 * 模拟接口--服务端
 *
 * @author 沉梦
 */

@RestController
@RequestMapping("/invoke")
public class NameController {

    @GetMapping("/{name}")
    public String getNameByGet(@PathVariable(value = "name") String name) {
        return "发送GET请求 你的名字是：" + name;
    }

    @PostMapping("/po")
    public String getNameByPost(@RequestParam(value = "name") String name) {
        return "发送POST请求 你的名字是：" + name;
    }

    @PostMapping("/user")
    public String getNameByUser(@RequestBody User user, HttpServletRequest request) {
        return "获取的用户名称是：" + user.getName();
    }

    /**
     * 随机一句诗歌
     * @param request
     * @return
     */
    @GetMapping("/poetry")
    public String getPoetry(HttpServletRequest request){

        String result = HttpUtil.get("https://api.apiopen.top/api/sentences"
                , CharsetUtil.CHARSET_UTF_8);

        if (StringUtils.isBlank(result)){
            return "此接口发生了意外55！！";
        }

        return result;
    }

    /**
     * 获取 NBA 赛事
     *
     * @param request 请求
     * @return {@code String}
     */
    @GetMapping("/nbaEvent")
    public String getNBAEvent(HttpServletRequest request){

        String result = HttpUtil.get("https://www.juhe.cn/docs/api/id/92"
                , CharsetUtil.CHARSET_UTF_8);

        if (StringUtils.isBlank(result)){
            return "此接口发生了意外55！！";
        }

        return result;
    }

    /**
     * 获取当前时间
     *
     * @param request 请求
     * @return {@code String}
     */
    @GetMapping("/currentTime")
    public String getCurrentTime(HttpServletRequest request){

        String result = HttpUtil.get("https://api.apiopen.top/api/getTime"
                , CharsetUtil.CHARSET_UTF_8);

        if (StringUtils.isBlank(result)){
            return "此接口发生了意外55！！";
        }

        return result;
    }

    /**
     * 随机一言
     * @param request
     * @return
     */
    @GetMapping("/saying")
    public String getSaying(HttpServletRequest request){

        String result = HttpUtil.get("https://api.apiopen.top/api/sentences"
                , CharsetUtil.CHARSET_UTF_8);

        if (StringUtils.isBlank(result)){
            return "此接口发生了意外55！！";
        }

        return result;
    }

    /**
     * 抖音无水印解析：https://api.aagtool.top/api/dywsyjx
     * @param req
     * @param request
     * @return
     */
    @PostMapping("/dou")
    public String getDouYinUrl(@RequestBody RequestParams req, HttpServletRequest request) {

        String result = HttpUtil.get("https://api.aagtool.top/api/dywsyjx?url=" + req.getUrl());

        if (StringUtils.isBlank(result)){
            return "解析失败！！";
        }
        return result;
    }

}
