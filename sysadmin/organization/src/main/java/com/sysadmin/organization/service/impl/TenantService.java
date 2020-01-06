package com.sysadmin.organization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysadmin.organization.dao.TenantMapper;
import com.sysadmin.organization.entity.param.TenantQueryParam;
import com.sysadmin.organization.entity.po.Tenant;
import com.sysadmin.organization.entity.vo.TenantVo;
import com.sysadmin.organization.service.ITenantService;
import com.sysadmin.organization.service.ITenantUserService;
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
//    @CacheEvict(value = "tenant", key = "#root.targetClass.name+'-'+#id")
    public void delete(Integer id) {
        tenantMapper.deleteById(id);
        tenantUserService.removeByTenantId(id);
    }

    @Override
//    @CacheEvict(value = "tenant", key = "#root.targetClass.name+'-'+#tenant.id")
    public void update(Tenant tenant) {
        tenantMapper.updateById(tenant);
    }

    @Override
//    @Cacheable(value = "tenant", key = "#root.targetClass.name+'-'+#id")
    public Tenant get(Integer id) {
        return tenantMapper.selectById(id);
    }


    @Override
    public IPage<TenantVo> query(Page page, TenantQueryParam tenantQueryParam) {
        QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != tenantQueryParam.getCreatedTimeStart(), "created_time",
                tenantQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != tenantQueryParam.getCreatedTimeEnd(), "created_time", tenantQueryParam.getCreatedTimeEnd());

        queryWrapper.eq(StringUtils.isNotBlank(tenantQueryParam.getTenantName()), "tenant_name", tenantQueryParam.getTenantName());
        // 转换成VO
        IPage iPage = tenantMapper.selectPage(page, queryWrapper).convert((tenant) -> {
            TenantVo tenantVo = new TenantVo();
            BeanUtils.copyProperties(tenant, tenantVo);
            return tenantVo;
        });
        return iPage;
    }
}
