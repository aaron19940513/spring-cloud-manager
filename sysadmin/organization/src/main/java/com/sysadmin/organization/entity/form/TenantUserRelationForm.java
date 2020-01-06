package com.sysadmin.organization.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class TenantUserRelationForm  {

    @ApiModelProperty(value = "租户Id")
    @NotNull(message = "租户Id不能为空")
    private Integer tenantId;

    @ApiModelProperty(value = "用户Id")
    @NotNull(message = "用户Id不能为空")
    private Integer userId;


}
