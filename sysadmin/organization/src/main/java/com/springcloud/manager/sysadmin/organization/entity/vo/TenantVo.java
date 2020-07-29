package com.springcloud.manager.sysadmin.organization.entity.vo;

import com.springcloud.manager.common.core.entity.vo.BaseVo;
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
public class TenantVo extends BaseVo {
    private Integer id = 0;
    private String tenantName;
    private String description;
    private Boolean enabled;
    private String deleted;

}
