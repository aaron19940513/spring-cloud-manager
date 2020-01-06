package com.sysadmin.organization.entity.form;

import com.springboot.cloud.common.web.entity.form.BaseForm;
import com.sysadmin.organization.entity.po.Tenant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ApiModel
@Data
public class TenantForm extends BaseForm<Tenant> {

    @ApiModelProperty(value = "租户账号")
    @NotBlank(message = "租户名不能为空")
    @Length(min = 3, max = 20, message = "租户名长度在3到20个字符")
    private String tenantName;

    @ApiModelProperty(value = "租户描述")
    private String description;

    @ApiModelProperty(value = "租户状态，true为可用")
    private Boolean enabled = true;


}
