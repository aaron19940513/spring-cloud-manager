package com.springcloud.manager.sysadmin.organization.entity.form;

import java.util.Date;

import javax.validation.constraints.Past;

import com.springcloud.manager.common.web.entity.form.BaseQueryForm;
import com.springcloud.manager.sysadmin.organization.entity.param.RoleQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel
@Data
public class RoleQueryForm extends BaseQueryForm<RoleQueryParam> {

    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createdTimeEnd;
}
