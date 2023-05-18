package com.chenmeng.project.service.impl.inner;

import com.chenmeng.cmapicommon.service.InnerUserInterfaceInfoService;
import com.chenmeng.project.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeInterfaceCount(Long interfaceInfoId, Long userId) {
        return userInterfaceInfoService.invokeInterfaceCount(interfaceInfoId, userId);
    }
}