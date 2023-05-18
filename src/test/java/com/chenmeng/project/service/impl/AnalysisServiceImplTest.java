package com.chenmeng.project.service.impl;

import com.chenmeng.project.common.ResultUtils;
import com.chenmeng.project.model.vo.InterfaceInfoVO;
import com.chenmeng.project.service.AnalysisService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnalysisServiceImplTest {

    @Resource
    private AnalysisService analysisService;

    @Test
    void listTopInvokeInterfaceInfo() {
        List<InterfaceInfoVO> list = analysisService.listTopInvokeInterfaceInfo(3);
        System.out.println(list);
    }

}