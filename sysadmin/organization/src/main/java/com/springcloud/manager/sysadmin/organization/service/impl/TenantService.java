package com.springcloud.manager.sysadmin.organization.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.manager.sysadmin.organization.dao.TenantMapper;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Tenant;
import com.springcloud.manager.sysadmin.organization.entity.vo.TenantVo;
import com.springcloud.manager.sysadmin.organization.service.ITenantService;
import com.springcloud.manager.sysadmin.organization.service.ITenantUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TenantService implements ITenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private ITenantUserService tenantUserService;

    @Override
    public Integer add(Tenant tenant) {
        return tenantMapper.insert(tenant);
    }

    @Override
    @CacheInvalidate(name = "tenant::", key = "#id")
    public void delete(Integer id) {
        tenantMapper.deleteById(id);
        tenantUserService.removeByTenantId(id);
    }

    @Override
    @CacheUpdate(name = "tenant::", key = "#tenant.id", value = "#tenant")
    public void update(Tenant tenant) {
        tenantMapper.updateById(tenant);
    }

    @Override
    public TenantVo get(Integer id) {
        TenantVo tenantVo = new TenantVo();
        Tenant tenant = getById(id);
        BeanUtils.copyProperties(tenant, tenantVo);
        return tenantVo;
    }

    @Cached(name = "tenant::", key = "#id", cacheType = CacheType.BOTH)
    public Tenant getById(Integer id) {
        return tenantMapper.selectById(id);
    }

    @Override
    public IPage<TenantVo> query(Page page, TenantQueryParam tenantQueryParam) {
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != tenantQueryParam.getCreatedTimeStart(), "created_time",
                        tenantQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != tenantQueryParam.getCreatedTimeEnd(), "created_time", tenantQueryParam.getCreatedTimeEnd());

        queryWrapper.eq(StringUtils.isNotBlank(tenantQueryParam.getTenantName()), "tenant_name", tenantQueryParam.getTenantName());
        queryWrapper.eq(StringUtils.isNotBlank(tenantQueryParam.getCreatedBy()), "created_by", tenantQueryParam.getCreatedBy());
        queryWrapper.eq(null != tenantQueryParam.getEnabled(), "enabled", tenantQueryParam.getEnabled());
        // 转换成VO
        IPage<TenantVo> iPage = tenantMapper.selectPage(page, queryWrapper).convert((tenant) -> {
            TenantVo tenantVo = new TenantVo();
            BeanUtils.copyProperties(tenant, tenantVo);
            return tenantVo;
        });
        return iPage;
    }

    @Override
    public List<TenantVo> queryAll() {
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
        List<Tenant> tenants = tenantMapper.selectList(queryWrapper);
        List<TenantVo> tenantVos = tenants.stream().map(tenant -> {
            TenantVo tenantVo = new TenantVo();
            BeanUtils.copyProperties(tenant, tenantVo);
            return tenantVo;
        }).collect(Collectors.toList());
        return tenantVos;
    }
}
