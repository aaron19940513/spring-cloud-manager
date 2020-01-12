package com.sysadmin.organization.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.cloud.common.core.entity.vo.UserInfo;
import com.sysadmin.organization.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select  tenant.id as tenantId,tenant.tenant_name as tenantName,users.id as userId,users.username as userName,users.name as name " +
            "from tenant left join " +
            "tenant_user_relation relation on tenant.id = relation.tenant_id left join users on relation.user_id = users.id " +
            "where users.deleted ='N' and users.enabled = 1")
    List<UserInfo> queryAllUserInfo();
}