package com.chenmeng.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenmeng.cmapicommon.model.entity.UserInterfaceInfo;
import com.chenmeng.cmapicommon.service.InnerUserInterfaceInfoService;
import com.chenmeng.project.common.ErrorCode;
import com.chenmeng.project.exception.BusinessException;
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

    @Override
    public boolean hasInvokeNum(long userId, long interfaceInfoId) {
        if (userId <= 0 || interfaceInfoId <= 0) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        LambdaQueryWrapper<UserInterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserInterfaceInfo::getUserId, userId)
                .eq(UserInterfaceInfo::getInterfaceInfoId, interfaceInfoId)
                .gt(UserInterfaceInfo::getLeftNum, 0);

        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getBaseMapper().selectOne(lambdaQueryWrapper);

        return userInterfaceInfo != null;
    }
}