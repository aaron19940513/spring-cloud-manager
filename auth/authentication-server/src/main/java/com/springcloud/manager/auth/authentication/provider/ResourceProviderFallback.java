package com.springcloud.manager.auth.authentication.provider;

import com.springcloud.manager.auth.authentication.po.Resource;
import com.springcloud.manager.common.core.entity.vo.Result;

import java.util.HashSet;
import java.util.Set;

public class ResourceProviderFallback implements ResourceProvider {
    @Override
    public Result<Set<Resource>> resources() {
        return Result.success(new HashSet<Resource>());
    }

    @Override
    public Result<Set<Resource>> resources(String username) {
        return Result.success(new HashSet<Resource>());
    }
}
