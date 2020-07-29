package com.springcloud.manager.sysadmin.organization.rest;

import javax.validation.Valid;

import com.springcloud.manager.common.core.entity.vo.Result;
import com.springcloud.manager.sysadmin.organization.entity.form.TenantUserRelationForm;
import com.springcloud.manager.sysadmin.organization.entity.form.TenantUserRelationQueryForm;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantUserRelationQueryParam;
import com.springcloud.manager.sysadmin.organization.service.ITenantUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant/user")
@Api("tenant-user")
@Slf4j
public class TenantUserController {

    @Autowired
    private ITenantUserService tenantUserService;

    @ApiOperation(value = "租户关联与用户关系", notes = "租户关联用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "tenantUserRelationForm", value = "租户实体", required = true, dataType = "TenantUserRelationForm")})
    @PostMapping
    public Result add(@Valid @RequestBody TenantUserRelationForm tenantUserRelationForm) {
        tenantUserService.save(tenantUserRelationForm.getTenantId(), tenantUserRelationForm.getUserId());
        return Result.success();
    }

    @ApiOperation(value = "租户关联与用户关系", notes = "租户关联用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "tenantUserRelationQueryForm", value = "租户实体", required = true, dataType = "TenantUserRelationQueryForm")})
    @PostMapping("/condtions")
    public Result search(@Valid @RequestBody TenantUserRelationQueryForm tenantUserRelationQueryForm) {
        return Result.success(tenantUserService.query(tenantUserRelationQueryForm.getPage(), tenantUserRelationQueryForm.toParam(TenantUserRelationQueryParam.class)));
    }

    @ApiOperation(value = "租户解除与用户关系", notes = "租户解除与用户关系")
    @ApiImplicitParams({@ApiImplicitParam(name = "tenantUserRelationForm", value = "租户实体", required = true, dataType = "TenantUserRelationForm")})
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        tenantUserService.delete(id);
        return Result.success();
    }

}