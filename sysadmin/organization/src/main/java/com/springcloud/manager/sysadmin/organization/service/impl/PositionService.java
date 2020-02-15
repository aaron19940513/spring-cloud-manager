package com.springcloud.manager.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.manager.sysadmin.organization.dao.PositionMapper;
import com.springcloud.manager.sysadmin.organization.entity.param.PositionQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Position;
import com.springcloud.manager.sysadmin.organization.service.IPositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionService implements IPositionService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public Integer add(Position position) {
        return positionMapper.insert(position);
    }

    @Override
    @CacheInvalidate(name = "position::", key = "#id")
    public void delete(Integer id) {
        positionMapper.deleteById(id);
    }

    @Override
    @CacheUpdate(name = "position::", key = "#position.id", value = "#position")
    public void update(Position position) {
        positionMapper.updateById(position);
    }

    @Override
    @Cached(name = "position", key = "#id", cacheType = CacheType.BOTH)
    public Position get(Integer id) {
        return positionMapper.selectById(id);
    }

    @Override
    public List<Position> query(PositionQueryParam positionQueryParam) {
        QueryWrapper<Position> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != positionQueryParam.getName(), "name", positionQueryParam.getName());
        return positionMapper.selectList(queryWrapper);
    }

}
