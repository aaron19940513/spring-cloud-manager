package com.springcloud.manager.sysadmin.organization.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.manager.sysadmin.organization.entity.param.RoleQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Role;

public interface IRoleService {
    /**
     * 获取角色
     *
     * @param id
     * @return
     */
    Role get(Integer id);

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    Integer add(Role role);

    /**
     * 查询角色
     *
     * @return
     */
    IPage<Role> query(Page page, RoleQueryParam roleQueryParam);

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @return
     */
    List<Role> query(Integer userId);

    /**
     * 更新角色信息
     *
     * @param role
     */
    void update(Role role);

    /**
     * 根据id删除角色
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> getAll();
}
