package com.springcloud.manager.sysadmin.organization.entity.param;

import java.util.Date;

import com.springcloud.manager.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantQueryParam extends BaseParam {
    private String tenantName;
    private String createdBy;
    private Boolean enabled;
    private Date createdTimeStart;
    private Date createdTimeEnd;
}
