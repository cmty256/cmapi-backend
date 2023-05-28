package com.chenmeng.project.controller;

import com.chenmeng.project.annotation.AuthCheck;
import com.chenmeng.project.common.BaseResponse;
import com.chenmeng.project.common.ResultUtils;
import com.chenmeng.project.constant.UserConstant;
import com.chenmeng.project.model.vo.InterfaceInfoVO;
import com.chenmeng.project.service.AnalysisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

/**
 * 分析控制器
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    /**
     * 统计调用接口次数的排名列表
     *
     * @return {@code BaseResponse<List<InterfaceInfoVO>>}
     */
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<InterfaceInfoVO> listTopInvokeInterfaceInfo = analysisService.listTopInvokeInterfaceInfo(5);
        return ResultUtils.success(listTopInvokeInterfaceInfo);
    }
}
