package com.springcloud.manager.sysadmin.organization.entity.form;

import javax.validation.constraints.NotNull;

import com.springcloud.manager.common.web.entity.form.BaseForm;
import com.springcloud.manager.sysadmin.organization.entity.po.TenantUserRelation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TenantUserRelationForm extends BaseForm<TenantUserRelation> {

    @ApiModelProperty(value = "租户Id")
    @NotNull(message = "租户Id不能为空")
    private Integer tenantId;

    @ApiModelProperty(value = "用户Id")
    @NotNull(message = "用户Id不能为空")
    private Integer userId;


}
