package com.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysadmin.organization.entity.po.TenantUserRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TenantUserRelationMapper extends BaseMapper<TenantUserRelation> {
}