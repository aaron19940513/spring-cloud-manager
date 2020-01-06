package com.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysadmin.organization.entity.param.TenantQueryParam;
import com.sysadmin.organization.entity.po.Tenant;

public interface ITenantService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    Tenant get(Integer id);

    /**
     * 新增用户
     *
     * @param tenant
     * @return
     */
    Integer add(Tenant tenant);

    /**
     * 查询用户
     *
     * @return
     */
    IPage<Tenant> query(Page<Tenant> page, TenantQueryParam tenantQueryParam);

    /**
     * 更新用户信息
     *
     * @param tenant
     */
    void update(Tenant tenant);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void delete(Integer id);
}
