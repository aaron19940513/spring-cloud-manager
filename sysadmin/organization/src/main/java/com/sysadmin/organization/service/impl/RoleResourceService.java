package com.sysadmin.organization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysadmin.organization.dao.RoleResourceMapper;
import com.sysadmin.organization.entity.po.RoleResource;
import com.sysadmin.organization.service.IRoleResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleResourceService extends ServiceImpl<RoleResourceMapper, RoleResource> implements IRoleResourceService {

    @Override
    @Transactional
    public boolean saveBatch(Integer roleId, Set<Integer> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return false;
        }
        removeByRoleId(roleId);
        List<RoleResource> userRoles = new ArrayList<>();
        for (Integer resourceId : resourceIds) {
            userRoles.add(RoleResource.builder().roleId(roleId).resourceId(resourceId).build());
        }
        return saveBatch(userRoles);
    }

    @Override
    @Transactional
    public boolean removeByRoleId(Integer roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleResource::getRoleId, roleId);
        return remove(queryWrapper);
    }

    @Override
    public Set<Integer> queryByRoleId(Integer roleId) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        List<RoleResource> userRoleList = list(queryWrapper);
        return userRoleList.stream().map(RoleResource::getResourceId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleResource> queryByRoleIds(Set<Integer> roleIds) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return this.list(queryWrapper);
    }
}
