package com.springcloud.manager.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.springcloud.manager.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("tenant_user_relation")
public class TenantUserRelation extends BasePo {
    private Integer tenantId;
    private Integer userId;
}
