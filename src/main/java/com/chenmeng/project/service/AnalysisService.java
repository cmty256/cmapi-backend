package com.chenmeng.project.service;

import com.chenmeng.project.model.vo.InterfaceInfoVO;

import java.util.List;

public interface AnalysisService{

    List<InterfaceInfoVO> listTopInvokeInterfaceInfo(int limit);
}
