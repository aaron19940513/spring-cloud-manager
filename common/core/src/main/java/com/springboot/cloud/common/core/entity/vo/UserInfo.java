package com.springboot.cloud.common.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Integer tenantId;
    private String tenantName;
    private Integer userId;
    private String userName;
    private String name;
}
