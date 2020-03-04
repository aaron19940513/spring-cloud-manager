package com.springcloud.manager.sysadmin.organization.rest;

import javax.validation.Valid;

import com.springboot.cloud.common.core.entity.vo.Result;
import com.springcloud.manager.sysadmin.organization.entity.form.GroupForm;
import com.springcloud.manager.sysadmin.organization.entity.form.GroupQueryForm;
import com.springcloud.manager.sysadmin.organization.entity.param.GroupQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Group;
import com.springcloud.manager.sysadmin.organization.service.IGroupService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
@Api("group")
@Slf4j
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @ApiOperation(value = "新增用户组", notes = "新增一个用户组")
    @ApiImplicitParam(name = "groupForm", value = "新增用户组form表单", required = true, dataType = "GroupForm")
    @PostMapping
    public Result add(@Valid @RequestBody GroupForm groupForm) {
        log.debug("name:{}", groupForm);
        Group group = groupForm.toPo(Group.class);
        return Result.success(groupService.add(group));
    }

    @ApiOperation(value = "删除用户组", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户组ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        groupService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改用户组", notes = "修改指定用户组信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户组ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "groupForm", value = "用户组实体", required = true, dataType = "GroupForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @Valid @RequestBody GroupForm groupForm) {
        Group group = groupForm.toPo(Group.class);
        group.setId(id);
        groupService.update(group);
        return Result.success();
    }

    @ApiOperation(value = "获取用户组", notes = "获取指定用户组信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户组ID", required = true, dataType = "Integer")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable Integer id) {
        log.debug("get with id:{}", id);
        return Result.success(groupService.get(id));
    }

    @ApiOperation(value = "查询用户组", notes = "根据条件查询用户组信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "用户组名称", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping
    public Result query(@RequestParam String name) {
        log.debug("query with name:{}", name);
        GroupQueryParam groupQueryParam = new GroupQueryParam();
        groupQueryParam.setName(name);
        return Result.success(groupService.query(groupQueryParam));
    }

    @ApiOperation(value = "搜索用户组", notes = "根据条件查询用户组信息")
    @ApiImplicitParam(name = "groupQueryForm", value = "用户组查询参数", required = true, dataType = "GroupQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GroupQueryForm groupQueryForm) {
        log.debug("search with groupQueryForm:{}", groupQueryForm);
        return Result.success(groupService.query(groupQueryForm.toParam(GroupQueryParam.class)));
    }

    @ApiOperation(value = "根据父id查询用户组", notes = "根据父id查询用户组列表")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户组父ID", required = true, dataType = "Integer")
    @GetMapping(value = "/parent/{id}")
    public Result search(@PathVariable Integer id) {
        log.debug("query with parent id:{}", id);
        return Result.success(groupService.queryByParentId(id));
    }
}