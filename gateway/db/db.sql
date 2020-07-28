DROP DATABASE IF EXISTS sc_gateway;
CREATE DATABASE sc_gateway;

-- 网关路由表
DROP TABLE IF EXISTS gateway_routes;
CREATE TABLE gateway_routes
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'id',
    route_id     VARCHAR(100) NOT NULL COMMENT '路由id',
    uri          VARCHAR(100) NOT NULL COMMENT 'uri路径',
    predicates   TEXT         NOT NULL COMMENT '判定器',
    filters      TEXT COMMENT '过滤器',
    orders       INT COMMENT '排序',
    description  VARCHAR(500) COMMENT '描述',
    status       VARCHAR(1)            DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
    created_time DATETIME     NOT NULL DEFAULT now() COMMENT '创建时间',
    updated_time DATETIME     NOT NULL DEFAULT now() COMMENT '更新时间',
    created_by   VARCHAR(100) NOT NULL COMMENT '创建人',
    updated_by   VARCHAR(100) NOT NULL COMMENT '更新人'
) COMMENT '网关路由表';

CREATE UNIQUE INDEX ux_gateway_routes_uri ON gateway_routes (uri);




-- 用户
INSERT INTO gateway_routes (id, route_id, uri, predicates, filters, orders, description, status, created_time, updated_time, created_by, updated_by)
VALUES
(101,
 'authorization-server',
 'lb://authorization-server:8000',
 '[{"name":"Path","args":{"pattern":"/authorization-server/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '授权认证服务网关注册',
 'Y', now(), now(), 'system', 'system'),
(102,
 'authentication-server',
 'lb://authentication-server:8001',
 '[{"name":"Path","args":{"pattern":"/authentication-server/**"}}]',
 '[{"name":"StripPrefix","args":{"parts":"1"}}]',
 100,
 '签权服务网关注册',
 'Y', now(), now(), 'system', 'system')

