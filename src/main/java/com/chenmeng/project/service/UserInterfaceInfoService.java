package com.chenmeng.project.service;

import com.chenmeng.project.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 沉梦
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2023-05-06 20:12:07
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 校验
     *
     * @param userInterfaceInfo
     * @param add 是否为创建校验
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 统计用户调用接口的次数
     *
     * @return boolean
     */
    boolean invokeInterfaceCount(Long userId, Long interfaceInfoId);
}
