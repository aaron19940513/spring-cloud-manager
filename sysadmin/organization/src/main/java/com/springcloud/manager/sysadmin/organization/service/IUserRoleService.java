package com.springcloud.manager.sysadmin.organization.service;

import java.util.Set;

public interface IUserRoleService {

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveBatch(Integer userId, Set<Integer> roleIds);

    /**
     * 删除用户拥有的角色
     *
     * @param userId
     * @return
     */
    boolean removeByUserId(Integer userId);

    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param userId
     * @return
     */
    Set<Integer> queryByUserId(Integer userId);
}
