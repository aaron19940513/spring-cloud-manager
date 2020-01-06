package com.sysadmin.organization.rest;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.sysadmin.organization.entity.form.TenantForm;
import com.sysadmin.organization.entity.form.TenantQueryForm;
import com.sysadmin.organization.entity.form.TenantUserRelationForm;
import com.sysadmin.organization.entity.param.TenantQueryParam;
import com.sysadmin.organization.entity.po.Tenant;
import com.sysadmin.organization.service.ITenantService;
import com.sysadmin.organization.service.ITenantUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tenant")
@Api("tenant")
@Slf4j
public class TenantController {

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private ITenantUserService tenantUserService;

    @ApiOperation(value = "新增租户", notes = "新增一个租户")
    @ApiImplicitParam(name = "tenantForm", value = "新增租户form表单", required = true, dataType = "TenantForm")
    @PostMapping
    public Result add(@Valid @RequestBody TenantForm tenantForm) {
        Tenant tenant = tenantForm.toPo(Tenant.class);
        return Result.success(tenantService.add(tenant));
    }

    @ApiOperation(value = "删除租户", notes = "根据url的id来指定删除对象，逻辑删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "租户ID", required = true, dataType = "int")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        tenantService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改租户", notes = "修改指定租户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "id", value = "租户ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "tenantForm", value = "租户实体", required = true, dataType = "TenantForm")})
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @Valid @RequestBody TenantForm tenantForm) {
        Tenant tenant = tenantForm.toPo(Tenant.class);
        tenant.setId(id);
        tenantService.update(tenant);
        return Result.success();
    }

    @ApiOperation(value = "获取租户", notes = "获取指定租户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "租户ID", required = true, dataType = "int")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable Integer id) {
        log.debug("get with id:{}", id);
        return Result.success(tenantService.get(id));
    }

    @ApiOperation(value = "租户关联用户", notes = "租户关联用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "tenantUserRelationForm", value = "租户实体", required = true, dataType = "TenantUserRelationForm")})
    @PutMapping(value = "user")
    public Result update(@Valid @RequestBody TenantUserRelationForm tenantUserRelationForm) {
        tenantUserService.save(tenantUserRelationForm.getTenantId(), tenantUserRelationForm.getUserId());
        return Result.success();
    }


    @ApiOperation(value = "搜索租户", notes = "根据条件查询租户信息")
    @ApiImplicitParam(name = "tenantQueryForm", value = "租户查询参数", required = true, dataType = "TenantQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody TenantQueryForm tenantQueryForm) {
        log.debug("search with tenantQueryForm:{}", tenantQueryForm);
        return Result.success(tenantService.query(tenantQueryForm.getPage(), tenantQueryForm.toParam(TenantQueryParam.class)));
    }
}