package com.chenmeng.project.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通过 id 发送请求
 *
 * @author 沉梦
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}