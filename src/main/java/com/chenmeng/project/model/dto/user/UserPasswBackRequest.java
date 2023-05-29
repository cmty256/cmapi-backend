package com.chenmeng.project.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户密码找回请求体
 *
 * @author 沉梦
 */
@Data
public class UserPasswBackRequest implements Serializable {

    private String userAccount;

    private String email;

    private String newPassword;

    private String checkPassword;
}
