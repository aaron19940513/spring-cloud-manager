package com.springcloud.manager.sysadmin.organization.entity.form;

import javax.validation.constraints.NotBlank;

import com.springcloud.manager.common.web.entity.form.BaseForm;
import com.springcloud.manager.sysadmin.organization.entity.po.Group;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class GroupForm extends BaseForm<Group> {

    @NotBlank(message = "用户组父id不能为空")
    @ApiModelProperty(value = "用户组父id")
    private Integer parentId;

    @NotBlank(message = "用户组名称不能为空")
    @ApiModelProperty(value = "用户组名称")
    private String name;

    @ApiModelProperty(value = "用户组描述")
    private String description;
}
