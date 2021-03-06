package com.springcloud.manager.gateway.admin.entity.po;

import java.util.ArrayList;
import java.util.List;

import com.springcloud.manager.common.core.entity.po.BasePo;
import com.sun.jndi.toolkit.url.Uri;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDefinition extends BasePo {
    private Uri uri;
    private String routeId;
    private List<FilterDefinition> filters = new ArrayList<>();
    private List<PredicateDefinition> predicates = new ArrayList<>();
    private String description;
    private Integer orders;
    private String status;
}
