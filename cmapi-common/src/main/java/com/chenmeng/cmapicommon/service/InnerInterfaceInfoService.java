package com.chenmeng.cmapicommon.service;

import com.chenmeng.cmapicommon.model.entity.InterfaceInfo;


/**
 * 内部接口信息服务
 *
 * @author 沉梦听雨
 * @date 2023/05/17
 */
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     *
     * @param url   路径
     * @param method 方法
     * @return {@code InterfaceInfo}
     */
    InterfaceInfo getInterfaceInfo(String url, String method);
}
