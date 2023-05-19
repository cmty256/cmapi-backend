package com.chenmeng.cmapicommon.service;

/**
 * 内部用户接口信息服务
 *
 * @author 沉梦听雨
 * @date 2023/05/17
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 接口调用计数统计
     *
     * @param userId          用户id
     * @param interfaceInfoId 接口信息id
     * @return boolean
     */
    boolean invokeInterfaceCount(Long userId, Long interfaceInfoId);

    /**
     * 是否还有调用次数
     *
     * @param userId          用户id
     * @param interfaceInfoId 接口id
     * @return boolean
     */
    boolean hasInvokeNum(long userId, long interfaceInfoId);
}
