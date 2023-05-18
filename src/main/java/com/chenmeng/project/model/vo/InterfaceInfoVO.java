package com.chenmeng.project.model.vo;

import com.chenmeng.cmapicommon.model.entity.InterfaceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 调用接口信息视图
 *
 * @author 沉梦
 * @TableName user_interface_info
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterfaceInfoVO extends InterfaceInfo {

    /**
     * 接口调用次数
     */
    private Integer invokeNum;

    private static final long serialVersionUID = 1L;
}