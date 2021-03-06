package com.springcloud.manager.auth.authentication.service.impl;

import java.util.Set;

import com.google.common.collect.Sets;
import com.springcloud.manager.auth.authentication.po.Resource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.access.SecurityConfig;


public class AuthenticationServiceTest {

    @Test
    public void testIsMatch_假如存在如上资源信息_当给定包含在资源信息时_那么返回true() {
        AuthenticationService authenticationService = new AuthenticationService();
        Resource resource = new Resource();
        resource.setCode("user_manager:view");
        Set<Resource> resources = Sets.newHashSet(resource);
        Assert.assertTrue(authenticationService.isMatch(new SecurityConfig("user_manager:view"), resources));
    }

    @Test
    public void testIsMatch_假如存在如上资源信息_当给不包含在资源信息时_那么返回false() {
        AuthenticationService authenticationService = new AuthenticationService();
        Resource resource = new Resource();
        resource.setCode("user_manager:manager");
        Set<Resource> resources = Sets.newHashSet(resource);
        Assert.assertFalse(authenticationService.isMatch(new SecurityConfig("user_manager:view"), resources));
    }

}