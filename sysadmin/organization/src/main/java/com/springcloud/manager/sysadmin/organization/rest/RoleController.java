package com.springcloud.manager.sysadmin.organization.rest;

import javax.validation.Valid;

import com.springcloud.manager.common.core.entity.vo.Result;
import com.springcloud.manager.sysadmin.organization.entity.form.RoleForm;
import com.springcloud.manager.sysadmin.organization.entity.form.RoleQueryForm;
import com.springcloud.manager.sysadmin.organization.entity.form.RoleUpdateForm;
import com.springcloud.manager.sysadmin.organization.entity.param.RoleQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Role;
import com.springcloud.manager.sysadmin.organization.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@Api("role")
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增一个角色")
    @ApiImplicitParam(name = "roleForm", value = "新增角色form表单", required = true, dataType = "RoleForm")
    @PostMapping
    public Result add(@Valid @RequestBody RoleForm roleForm) {
        log.debug("name:{}", roleForm);
        Role role = roleForm.toPo(Role.class);
        return Result.success(roleService.add(role));
    }

    @ApiOperation(value = "删除角色", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色ID", required = true, dataType = "int")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        roleService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改角色", notes = "修改指定角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "roleForm", value = "角色实体", required = true, dataType = "RoleForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @Valid @RequestBody RoleUpdateForm roleUpdateForm) {
        Role role = roleUpdateForm.toPo(Role.class);
        role.setId(id);
        roleService.update(role);
        return Result.success();
    }

    @ApiOperation(value = "获取角色", notes = "获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色ID", required = true, dataType = "int")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable Integer id) {
        log.debug("get with id:{}", id);
        return Result.success(roleService.get(id));
    }

    @ApiOperation(value = "查询角色", notes = "根据用户id查询用户所拥有的角色信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "int")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping(value = "/user/{userId}")
    public Result query(@PathVariable Integer userId) {
        log.debug("query with userId:{}", userId);
        return Result.success(roleService.query(userId));
    }

    @ApiOperation(value = "获取所有角色", notes = "获取所有角色")
    @GetMapping(value = "/all")
    public Result get() {
        return Result.success(roleService.getAll());
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件搜索角色信息")
    @ApiImplicitParam(name = "roleQueryForm", value = "角色查询参数", required = true, dataType = "RoleQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result query(@Valid @RequestBody RoleQueryForm roleQueryForm) {
        log.debug("query with name:{}", roleQueryForm);
        return Result.success(roleService.query(roleQueryForm.getPage(), roleQueryForm.toParam(RoleQueryParam.class)));
    }
}