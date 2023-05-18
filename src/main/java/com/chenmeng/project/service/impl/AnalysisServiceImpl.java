package com.chenmeng.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenmeng.cmapicommon.model.entity.InterfaceInfo;
import com.chenmeng.cmapicommon.model.entity.UserInterfaceInfo;
import com.chenmeng.project.common.ErrorCode;
import com.chenmeng.project.exception.BusinessException;
import com.chenmeng.project.mapper.UserInterfaceInfoMapper;
import com.chenmeng.project.model.vo.InterfaceInfoVO;
import com.chenmeng.project.service.AnalysisService;
import com.chenmeng.project.service.InterfaceInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public List<InterfaceInfoVO> listTopInvokeInterfaceInfo(int limit) {
        List<InterfaceInfoVO> vos = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(limit);
        if (vos == null || vos.size() == 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 根据id查询接口名称
        LinkedHashMap<Long, InterfaceInfoVO> voHashMap = new LinkedHashMap<>(vos.size());
        for (InterfaceInfoVO vo : vos) {
            voHashMap.put(vo.getId(), vo);
        }
        LambdaQueryWrapper<InterfaceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(InterfaceInfo::getId, voHashMap.keySet());
        List<InterfaceInfo> infoList = interfaceInfoService.list(queryWrapper);

        for (InterfaceInfo interfaceInfo : infoList) {
            voHashMap.get(interfaceInfo.getId()).setName(interfaceInfo.getName());
        }
        return new ArrayList<>(voHashMap.values());
    }

}

