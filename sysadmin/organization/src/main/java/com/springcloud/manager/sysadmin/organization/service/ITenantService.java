package com.springcloud.manager.sysadmin.organization.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Tenant;
import com.springcloud.manager.sysadmin.organization.entity.vo.TenantVo;

public interface ITenantService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    TenantVo get(Integer id);

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
    IPage<TenantVo> query(Page<Tenant> page, TenantQueryParam tenantQueryParam);

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

    /**
     * 查询所有租户
     */
    List<TenantVo> queryAll();
}
