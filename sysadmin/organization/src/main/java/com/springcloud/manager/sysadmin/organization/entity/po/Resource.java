package com.springcloud.manager.sysadmin.organization.entity.po;

import com.springcloud.manager.common.core.entity.po.BasePo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends BasePo {
    private String code;
    private String type;
    private String url;
    private String method;
    private String name;
    private String description;
}
