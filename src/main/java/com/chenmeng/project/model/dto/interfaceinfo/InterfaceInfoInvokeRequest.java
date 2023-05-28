package com.chenmeng.project.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试 -- 调用接口请求
 *
 * @author 沉梦
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    private String interfaceName;
    /**
     * 请求参数
     */
    private String requestParams;

}