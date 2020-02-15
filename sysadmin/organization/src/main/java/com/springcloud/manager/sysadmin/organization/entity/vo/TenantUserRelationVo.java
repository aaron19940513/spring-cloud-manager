package com.springcloud.manager.sysadmin.organization.entity.vo;

import com.springboot.cloud.common.core.entity.vo.BaseVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantUserRelationVo extends BaseVo {
    private Integer tenantId;
    private String tenantName;
    private Integer userId;
    private String userName;
    private String description;
}
