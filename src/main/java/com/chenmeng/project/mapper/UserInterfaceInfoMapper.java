package com.chenmeng.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chenmeng.cmapicommon.model.entity.UserInterfaceInfo;
import com.chenmeng.project.model.vo.InterfaceInfoVO;

import java.util.List;

/**
* @author 沉梦
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2023-05-06 20:12:07
* @Entity com.chenmeng.cmapicommon.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<InterfaceInfoVO> listTopInvokeInterfaceInfo(int limit);
}




