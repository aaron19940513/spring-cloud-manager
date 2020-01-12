package com.sysadmin.organization.entity.param;

import com.springboot.cloud.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantQueryParam extends BaseParam {
    private String tenantName;
    private Date createdTimeStart;
    private Date createdTimeEnd;
}