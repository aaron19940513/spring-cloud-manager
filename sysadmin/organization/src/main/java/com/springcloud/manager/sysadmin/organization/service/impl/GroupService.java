package com.springcloud.manager.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.manager.sysadmin.organization.dao.GroupMapper;
import com.springcloud.manager.sysadmin.organization.entity.param.GroupQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Group;
import com.springcloud.manager.sysadmin.organization.service.IGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupService implements IGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Integer add(Group group) {
        return groupMapper.insert(group);
    }

    @Override
    @CacheInvalidate(name = "group::", key = "#id")
    public void delete(Integer id) {
        groupMapper.deleteById(id);
    }

    @Override
    @CacheUpdate(name = "group::", key = "#group.id", value = "#group")
    public void update(Group group) {
        groupMapper.updateById(group);
    }

    @Override
    @Cached(name = "group::", key = "#id", cacheType = CacheType.BOTH)
    public Group get(Integer id) {
        return groupMapper.selectById(id);
    }

    @Override
    public List<Group> query(GroupQueryParam groupQueryParam) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(null != groupQueryParam.getCreatedTimeStart(), "created_time", groupQueryParam.getCreatedTimeStart());
        queryWrapper.le(null != groupQueryParam.getCreatedTimeEnd(), "created_time", groupQueryParam.getCreatedTimeEnd());
        queryWrapper.eq("name", groupQueryParam.getName());
        return groupMapper.selectList(queryWrapper);
    }

    @Override
    public List<Group> queryByParentId(Integer id) {
        return groupMapper.selectList(new QueryWrapper<Group>().eq("parent_id", id));
    }
}
