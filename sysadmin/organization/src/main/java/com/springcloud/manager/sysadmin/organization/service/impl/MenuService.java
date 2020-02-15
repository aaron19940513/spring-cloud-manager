package com.springcloud.manager.sysadmin.organization.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springcloud.manager.sysadmin.organization.dao.MenuMapper;
import com.springcloud.manager.sysadmin.organization.entity.param.MenuQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Menu;
import com.springcloud.manager.sysadmin.organization.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public Integer add(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    @CacheInvalidate(name = "menu::", key = "#id")
    public void delete(Integer id) {
        menuMapper.deleteById(id);
    }

    @Override
    @CacheUpdate(name = "menu::", key = "#menu.id", value = "#menu")
    public void update(Menu menu) {
        menuMapper.updateById(menu);
    }

    @Override
    @Cached(name = "menu::", key = "#id", cacheType = CacheType.BOTH)
    public Menu get(Integer id) {
        return menuMapper.selectById(id);
    }

    @Override
    public List<Menu> query(MenuQueryParam menuQueryParam) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null != menuQueryParam.getName(), "name", menuQueryParam.getName());
        return menuMapper.selectList(queryWrapper);
    }

    @Override
    public List<Menu> queryByParentId(Integer id) {
        return menuMapper.selectList(new QueryWrapper<Menu>().eq("parent_id", id));
    }
}
