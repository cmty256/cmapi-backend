package com.chenmeng.cmapicommon.service;

import com.chenmeng.cmapicommon.model.entity.User;

/**
 * 内部用户服务
 *
 * @author 沉梦听雨
 * @date 2023/05/17
 */
public interface InnerUserService {

    /**
     * 得到调用用户
     * 在数据库中查，根据 accessKey 查询用户
     *
     * @param accessKey 访问密钥
     * @return {@code User}
     */
    User getInvokeUser(String accessKey);
}
