package com.chenmeng.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenmeng.cmapicommon.model.entity.InterfaceInfo;

/**
* @author 沉梦
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2023-04-23 10:58:17
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 校验
     *
     * @param interfaceInfo
     * @param add 是否为创建校验
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
