package com.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysadmin.organization.dao.RoleMapper;
import com.sysadmin.organization.dao.UserRoleMapper;
import com.sysadmin.organization.entity.param.RoleQueryParam;
import com.sysadmin.organization.entity.po.Role;
import com.sysadmin.organization.entity.po.UserRole;
import com.sysadmin.organization.service.IRoleResourceService;
import com.sysadmin.organization.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleService implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private IRoleResourceService roleResourceService;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Integer add(Role role) {
        return roleMapper.insert(role);
    }

    @Override
//    @CacheEvict(value = "role", key = "#root.targetClass.name+'-'+#id")
    public void delete(Integer id) {
        roleMapper.deleteById(id);
    }

    @Override
//    @CacheEvict(value = "role", key = "#root.targetClass.name+'-'+#role.id")
    public void update(Role role) {
        roleMapper.updateById(role);
        roleResourceService.saveBatch(role.getId(), role.getResourceIds());
    }

    @Override
//    @Cacheable(value = "role", key = "#root.targetClass.name+'-'+#id")
    public Role get(Integer id) {
        return roleMapper.selectById(id);
    }

    @Override
    public List<Role> query(Integer userId) {
        List<UserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<UserRole>().eq("user_id", userId));
        return roleMapper.selectBatchIds(userRoles.stream().map(userRole -> userRole.getRoleId()).collect(Collectors.toList()));
    }

    @Override
    public IPage<Role> query(Page page, RoleQueryParam roleQueryParam) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getName()), "name", roleQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(roleQueryParam.getCode()), "code", roleQueryParam.getCode());
        return roleMapper.selectPage(page, queryWrapper);
    }

}
