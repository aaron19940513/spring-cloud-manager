package com.sysadmin.organization.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.cloud.common.core.entity.vo.UserInfo;
import com.sysadmin.organization.dao.UserMapper;
import com.sysadmin.organization.entity.param.UserQueryParam;
import com.sysadmin.organization.entity.po.User;
import com.sysadmin.organization.entity.vo.UserVo;
import com.sysadmin.organization.service.IUserRoleService;
import com.sysadmin.organization.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService implements IUserService {

    private static final String USER_INFO = "user_info::";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public Integer add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
        userRoleService.saveBatch(user.getId(), user.getRoleIds());
        return 1;
    }

    @Override
    @CacheEvict(value = "user", key = "#root.targetClass.name+'-'+#id")
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    @CacheEvict(value = "user", key = "#root.targetClass.name+'-'+#user.id")
    public void update(User user) {
        if (StringUtils.isNotBlank(user.getPassword()))
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.updateById(user);
    }

    @Override
    @Cacheable(value = "user", key = "#root.targetClass.name+'-'+#id")
    public User get(Integer id) {
        User user = userMapper.selectById(id);
        if (Objects.isNull(user)) {
            throw new RuntimeException("user not found with id:" + id);
        }
        user.setRoleIds(userRoleService.queryByUserId(id));
        return user;
    }

    @Override
    public User getByUniqueId(String uniqueId) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username", uniqueId)
                .or()
                .eq("mobile", uniqueId));
    }

    @Override
    public IPage<UserVo> query(Page page, UserQueryParam userQueryParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != userQueryParam.getCreatedTimeStart(), "created_time",
                userQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != userQueryParam.getCreatedTimeEnd(), "created_time", userQueryParam.getCreatedTimeEnd());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getName()), "name", userQueryParam.getName());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getUsername()), "username", userQueryParam.getUsername());
        queryWrapper.eq(StringUtils.isNotBlank(userQueryParam.getMobile()), "mobile", userQueryParam.getMobile());
        // 转换成VO
        IPage iPage = userMapper.selectPage(page, queryWrapper).convert((user) -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            return userVo;
        });
        return iPage;
    }

    @Override
    public boolean loadUsers() {
        List<UserInfo> userInfos = userMapper.queryAllUserInfo();
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        userInfos.forEach(userInfo ->
                opsForValue.set(USER_INFO + userInfo.getUserName(), JSON.toJSONString(userInfo))
        );
        return true;
    }
}
