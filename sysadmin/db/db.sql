DROP DATABASE IF EXISTS sc_admin;
CREATE DATABASE sc_admin;

-- 用户组表
DROP TABLE IF EXISTS groups;
CREATE TABLE groups
(
    id           SERIAL PRIMARY KEY COMMENT 'id',
    parent_id    INT          NOT NULL COMMENT '用户组父id',
    name         VARCHAR(200) COMMENT '用户组名称',
    description  VARCHAR(500) COMMENT '描述',
    deleted      VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户组表';

-- 岗位表
DROP TABLE IF EXISTS position;
CREATE TABLE position
(
    id           SERIAL PRIMARY KEY COMMENT 'id',
    name         VARCHAR(200) COMMENT '岗位名称',
    description  VARCHAR(500) COMMENT '描述',
    deleted      VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '岗位表';

-- 菜单表
DROP TABLE IF EXISTS menu;
CREATE TABLE menu
(
    id           SERIAL PRIMARY KEY COMMENT 'id',
    parent_id    INT          NOT NULL COMMENT '父菜单id',
    type         VARCHAR(100) COMMENT '菜单类型',
    href         VARCHAR(200) COMMENT '菜单路径',
    icon         VARCHAR(200) COMMENT '菜单图标',
    name         VARCHAR(200) COMMENT '菜单名称',
    description  VARCHAR(500) COMMENT '描述',
    order_num    INTEGER COMMENT '创建时间',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '菜单表';


-- 用户和组关系表
DROP TABLE IF EXISTS user_group_relation;
CREATE TABLE user_group_relation
(
    id           SERIAL PRIMARY KEY COMMENT 'id',
    user_id      INT          NOT NULL COMMENT '用户id',
    group_id     INT          NOT NULL COMMENT '用户组id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户和组关系表';


-- 用户和岗位系表
DROP TABLE IF EXISTS user_position_relation;
CREATE TABLE user_position_relation
(
    id           SERIAL PRIMARY KEY COMMENT 'id',
    user_id      INT          NOT NULL COMMENT '用户id',
    position_id  INT          NOT NULL COMMENT '角色id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户和岗位关系表';


-- 角色和菜单关系表
DROP TABLE IF EXISTS role_menu_relation;
CREATE TABLE role_menu_relation
(
    id           SERIAL PRIMARY KEY COMMENT 'id',
    menu_id      INT          NOT NULL COMMENT '菜单id',
    role_id      INT          NOT NULL COMMENT '角色id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '角色和菜单关系表';


--  用户表
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户id',
    username                VARCHAR(100) NOT NULL COMMENT '用户名',
    password                VARCHAR(100) NOT NULL COMMENT '用户密码密文',
    name                    VARCHAR(200) COMMENT '用户姓名',
    mobile                  VARCHAR(20) COMMENT '用户手机',
    description             VARCHAR(500) COMMENT '简介',
    deleted                 VARCHAR(1)   NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
    enabled                 BOOLEAN COMMENT '是否有效用户',
    account_non_expired     BOOLEAN COMMENT '账号是否未过期',
    credentials_non_expired BOOLEAN COMMENT '密码是否未过期',
    account_non_locked      BOOLEAN COMMENT '是否未锁定',
    created_time            DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time            DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by              VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by              VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户表';
CREATE UNIQUE INDEX ux_users_username
    ON users (username);
CREATE UNIQUE INDEX ux_users_mobile
    ON users (mobile);

--  角色表
DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '角色id',
    code         VARCHAR(100) NOT NULL COMMENT '角色code',
    name         VARCHAR(200) COMMENT '角色名称',
    description  VARCHAR(500) COMMENT '简介',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '角色表';

-- 资源表
DROP TABLE IF EXISTS resource;
CREATE TABLE resource
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '资源id',
    code         VARCHAR(100) NOT NULL COMMENT '资源code',
    type         VARCHAR(100) NOT NULL COMMENT '资源类型',
    name         VARCHAR(200) NOT NULL COMMENT '资源名称',
    url          VARCHAR(200) NOT NULL COMMENT '资源url',
    method       VARCHAR(20)  NOT NULL COMMENT '资源方法',
    description  VARCHAR(500) COMMENT '简介',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '资源表';
CREATE UNIQUE INDEX ux_resource_code
    ON resource (code);

-- 用户和角色关系表
DROP TABLE IF EXISTS users_roles_relation;
CREATE TABLE users_roles_relation
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关系id',
    user_id      INT          NOT NULL COMMENT '用户id',
    role_id      INT          NOT NULL COMMENT '角色id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '用户和角色关系表';

-- 角色和资源关系表
DROP TABLE IF EXISTS roles_resources_relation;
CREATE TABLE roles_resources_relation
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '关系id',
    resource_id  INT          NOT NULL COMMENT '角色id',
    role_id      INT          NOT NULL COMMENT '资源id',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '角色和资源关系表';