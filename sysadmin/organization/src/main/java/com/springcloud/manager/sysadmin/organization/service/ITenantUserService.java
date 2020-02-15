package com.springcloud.manager.sysadmin.organization.service;


import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantUserRelationQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.TenantUserRelation;
import com.springcloud.manager.sysadmin.organization.entity.vo.TenantUserRelationVo;

public interface ITenantUserService {
    /**
     * 租户添加用户
     *
     * @param tenantId 租户id
     * @param userId   用户id
     * @return 是否操作成功
     */
    boolean save(Integer tenantId, Integer userId);

    /**
     * 批量给租户添加用户
     *
     * @param tenantId 租户id
     * @param userIds  用户id列表
     * @return 是否操作成功
     */
    boolean saveBatch(Integer tenantId, Set<Integer> userIds);

    /**
     * 删除租户拥有的用户
     *
     * @param tenantId 租户id
     * @return 是否操作成功
     */
    boolean removeByTenantId(Integer tenantId);

    /**
     * 删除用户的关联关系
     *
     * @param userId 用户Id
     * @return 是否操作成功
     */
    boolean removeByUserId(Integer userId);

    /**
     * 查询租户拥有用户id
     *
     * @param tenantId 租户id
     * @return 租户拥有的用户id集合
     */
    Set<Integer> queryByTenantId(Integer tenantId);

    /**
     * 根据租户id列表查询用户关系
     *
     * @param tenantIds 租户id集合
     * @return 租户用户关系集合
     */
    List<TenantUserRelation> queryByTenantIds(Set<Integer> tenantIds);

    /**
     * 删除租户与用户的关联关系
     *
     * @param id id
     */
    void delete(Integer id);

    IPage<TenantUserRelationVo> query(Page page, TenantUserRelationQueryParam toParam);

    /**
     * 查询用户关联的租户
     *
     * @param userId 租户id
     * @return 关联的租户
     */
    Integer queryTenantIdByUserId(Integer userId);
}
