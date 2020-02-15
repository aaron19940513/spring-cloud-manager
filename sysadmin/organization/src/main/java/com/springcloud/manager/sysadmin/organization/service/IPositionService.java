package com.springcloud.manager.sysadmin.organization.service;

import com.springcloud.manager.sysadmin.organization.entity.param.PositionQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.Position;

import java.util.List;

public interface IPositionService {
    /**
     * 获取职位
     *
     * @param id
     * @return
     */
    Position get(Integer id);

    /**
     * 新增职位
     *
     * @param position
     * @return
     */
    Integer add(Position position);

    /**
     * 查询职位
     *
     * @return
     */
    List<Position> query(PositionQueryParam positionQueryParam);

    /**
     * 更新职位信息
     *
     * @param position
     */
    void update(Position position);

    /**
     * 根据id删除职位
     *
     * @param id
     */
    void delete(Integer id);
}
