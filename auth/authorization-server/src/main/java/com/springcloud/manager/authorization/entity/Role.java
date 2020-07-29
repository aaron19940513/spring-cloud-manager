package com.springcloud.manager.authorization.entity;

import com.springcloud.manager.common.core.entity.po.BasePo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false )
@NoArgsConstructor
public class Role extends BasePo {
    private String code;
    private String name;
    private String description;
}
