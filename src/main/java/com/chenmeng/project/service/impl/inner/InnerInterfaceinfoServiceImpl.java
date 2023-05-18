package com.chenmeng.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chenmeng.cmapicommon.model.entity.InterfaceInfo;
import com.chenmeng.cmapicommon.service.InnerInterfaceInfoService;
import com.chenmeng.project.common.ErrorCode;
import com.chenmeng.project.exception.BusinessException;
import com.chenmeng.project.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceinfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InterfaceInfo::getUrl, url);
        lambdaQueryWrapper.eq(InterfaceInfo::getMethod, method);
        return interfaceInfoMapper.selectOne(lambdaQueryWrapper);
    }
}
