package com.springcloud.manager.authorization.provider;

import com.springcloud.manager.authorization.entity.Role;
import com.springcloud.manager.authorization.entity.User;
import com.springboot.cloud.common.core.entity.vo.Result;

import java.util.HashSet;
import java.util.Set;

public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Result<User> getUserByUniqueId(String uniqueId) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> queryRolesByUserId(long userId) {
        return Result.success(new HashSet<Role>());
    }
}
