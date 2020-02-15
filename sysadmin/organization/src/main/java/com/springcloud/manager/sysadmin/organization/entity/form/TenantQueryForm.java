package com.springcloud.manager.sysadmin.organization.entity.form;

import java.util.Date;

import javax.validation.constraints.Past;

import com.springboot.cloud.common.web.entity.form.BaseQueryForm;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class TenantQueryForm extends BaseQueryForm<TenantQueryParam> {

    @ApiModelProperty(value = "租户名")
    private String tenantName;

    @ApiModelProperty(value = "是否有效")
    private Boolean enabled;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createdTimeEnd;
}
