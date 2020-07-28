package com.springcloud.manager.authorization.service.impl;

import com.springcloud.manager.authorization.entity.User;
import com.springcloud.manager.authorization.provider.OrganizationProvider;
import com.springcloud.manager.authorization.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
