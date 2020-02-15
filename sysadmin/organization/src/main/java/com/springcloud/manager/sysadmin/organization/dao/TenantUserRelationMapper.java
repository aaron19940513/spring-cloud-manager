package com.springcloud.manager.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springcloud.manager.sysadmin.organization.entity.param.TenantUserRelationQueryParam;
import com.springcloud.manager.sysadmin.organization.entity.po.TenantUserRelation;
import com.springcloud.manager.sysadmin.organization.entity.vo.TenantUserRelationVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TenantUserRelationMapper extends BaseMapper<TenantUserRelation> {
    IPage<TenantUserRelationVo> selectRelationVo(Page<?> page, @Param("relation") TenantUserRelationQueryParam tenantUserRelationQueryParam);
}