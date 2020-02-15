package com.springcloud.manager.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseQueryForm;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantUserRelationQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class TenantUserRelationQueryForm extends BaseQueryForm<TenantUserRelationQueryParam> {

    @ApiModelProperty(value = "租户Id")
    private Integer tenantId;

    @ApiModelProperty(value = "用户Id")
    private Integer userId;


}
