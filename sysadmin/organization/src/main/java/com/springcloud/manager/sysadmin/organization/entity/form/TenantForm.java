package com.springcloud.manager.sysadmin.organization.entity.form;

import javax.validation.constraints.NotBlank;

import com.springcloud.manager.common.web.entity.form.BaseForm;
import com.springcloud.manager.sysadmin.organization.entity.po.Tenant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel
@Data
public class TenantForm extends BaseForm<Tenant> {

    @ApiModelProperty(value = "租户账号")
    @NotBlank(message = "租户名不能为空")
    @Length(min = 2, max = 100, message = "租户名长度在3到100个字符")
    private String tenantName;

    @ApiModelProperty(value = "租户描述")
    private String description;

    @ApiModelProperty(value = "租户状态，true为可用")
    private Boolean enabled = true;


}
