package com.springcloud.manager.sysadmin.organization.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springcloud.manager.sysadmin.organization.dao.TenantUserRelationMapper;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantUserRelationQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.TenantUserRelation;
import com.springcloud.manager.sysadmin.organization.entity.vo.TenantUserRelationVo;
import com.springcloud.manager.sysadmin.organization.service.ITenantUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class TenantUserService extends ServiceImpl<TenantUserRelationMapper, TenantUserRelation> implements ITenantUserService {

    @Autowired
    TenantUserRelationMapper tenantUserRelationMapper;

    @Override
    @Transactional
    public boolean save(Integer tenantId, Integer userId) {
        if (null == userId) {
            return false;
        }
        removeByUserId(userId);
        Boolean result = false;
        if (null != tenantId) {
            TenantUserRelation tenantUserRelations = TenantUserRelation.builder().tenantId(tenantId).userId(userId).build();
            result = save(tenantUserRelations);
        }

        return result;
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
    @Transactional
    public boolean removeByUserId(Integer userId) {
        QueryWrapper<TenantUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TenantUserRelation::getUserId, userId);
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

    @Override
    public void delete(Integer id) {
        this.delete(id);
    }

    @Override
    public IPage<TenantUserRelationVo> query(Page page, TenantUserRelationQueryParam tenantUserRelationQueryParam) {
        return tenantUserRelationMapper.selectRelationVo(page, tenantUserRelationQueryParam);
    }

    @Override
    public Integer queryTenantIdByUserId(Integer userId) {
        QueryWrapper<TenantUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        TenantUserRelation tenantUserRelation = getOne(queryWrapper);
        return tenantUserRelation != null ? tenantUserRelation.getTenantId() : null;
    }
}
