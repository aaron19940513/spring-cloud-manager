package com.sysadmin.organization.entity.vo;

import com.springboot.cloud.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantVo extends BaseVo {
    private Integer id = 0;
    private String tenantName;
    private String description;
    private String deleted;
    private String createdBy;
    private String updatedBy;
    private Date createdTime;
    private Date updatedTime;
}
