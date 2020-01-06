package com.sysadmin.organization.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysadmin.organization.dao.TenantUserRelationMapper;
import com.sysadmin.organization.entity.po.TenantUserRelation;
import com.sysadmin.organization.service.ITenantUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TenantUserService extends ServiceImpl<TenantUserRelationMapper, TenantUserRelation> implements ITenantUserService {


    @Override
    @Transactional
    public boolean save(Integer tenantId, Integer userId) {
        if (null == userId) {
            return false;
        }
        List<TenantUserRelation> tenantUserRelations = new ArrayList<>();
        tenantUserRelations.add(TenantUserRelation.builder().tenantId(tenantId).userId(userId).build());
        return saveBatch(tenantUserRelations);
    }

    @Override
    @Transactional
    public boolean saveBatch(Integer tenantId, Set<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return false;
        }
        List<TenantUserRelation> userTenants = new ArrayList<>();
        for (Integer userId : userIds) {
            userTenants.add(TenantUserRelation.builder().tenantId(tenantId).userId(userId).build());
        }
        return saveBatch(userTenants);
    }

    @Override
    @Transactional
    public boolean removeByTenantId(Integer tenantId) {
        QueryWrapper<TenantUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TenantUserRelation::getTenantId, tenantId);
        return remove(queryWrapper);
    }

    @Override
    public Set<Integer> queryByTenantId(Integer tenantId) {
        QueryWrapper<TenantUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_id", tenantId);
        List<TenantUserRelation> userTenantList = list(queryWrapper);
        return userTenantList.stream().map(TenantUserRelation::getUserId).collect(Collectors.toSet());
    }

    @Override
    public List<TenantUserRelation> queryByTenantIds(Set<Integer> tenantIds) {
        QueryWrapper<TenantUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("tenant_id", tenantIds);
        return this.list(queryWrapper);
    }
}
