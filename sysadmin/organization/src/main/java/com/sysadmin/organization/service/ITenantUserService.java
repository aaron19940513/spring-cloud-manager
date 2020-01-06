package com.sysadmin.organization.service;


import com.sysadmin.organization.entity.po.TenantUserRelation;

import java.util.List;
import java.util.Set;

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
}
