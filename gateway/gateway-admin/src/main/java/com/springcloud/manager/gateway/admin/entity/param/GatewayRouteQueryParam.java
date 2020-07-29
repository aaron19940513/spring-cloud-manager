package com.springcloud.manager.gateway.admin.entity.param;

import com.springcloud.manager.common.core.entity.param.BaseParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GatewayRouteQueryParam extends BaseParam {
    public GatewayRouteQueryParam(String uri) {
        this.uri = uri;
    }

    private String uri;
    private Date createdTimeStart;
    private Date createdTimeEnd;
}
