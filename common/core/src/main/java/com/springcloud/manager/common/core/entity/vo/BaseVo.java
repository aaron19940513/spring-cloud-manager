package com.springcloud.manager.common.core.entity.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseVo {
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
