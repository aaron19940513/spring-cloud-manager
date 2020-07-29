package com.springcloud.manager.sysadmin.organization.entity.param;

import com.springcloud.manager.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantUserRelationQueryParam extends BaseParam {
    private Integer tenantId;
    private Integer userId;
}
