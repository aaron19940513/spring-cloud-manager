package com.sysadmin.organization.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sysadmin.organization.entity.param.UserQueryParam;
import com.sysadmin.organization.entity.po.User;

public interface IUserService {
    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    User get(Integer id);

    /**
     * 根据用户唯一标识获取用户信息
     * 目前用户标识不用户名或mobile
     *
     * @param uniqueId
     * @return
     */
    User getByUniqueId(String uniqueId);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    Integer add(User user);

    /**
     * 查询用户
     *
     * @return
     */
    IPage<User> query(Page<User> page, UserQueryParam userQueryParam);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void update(User user);

    /**
     * 根据id删除用户
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 加载用户
     *
     */
    boolean loadUsers();
}
