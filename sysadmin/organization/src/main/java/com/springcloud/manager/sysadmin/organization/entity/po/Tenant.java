package com.springcloud.manager.sysadmin.organization.entity.po;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("tenant")
public class Tenant extends BasePo {
    private String tenantName;
    private String description;
    private Boolean enabled;
    @TableLogic
    private String deleted = "N";
}
