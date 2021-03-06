package com.springcloud.manager.gateway.admin.rest;

import com.springcloud.manager.common.core.entity.vo.Result;
import com.springcloud.manager.gateway.admin.entity.form.GatewayRouteForm;
import com.springcloud.manager.gateway.admin.entity.form.GatewayRouteQueryForm;
import com.springcloud.manager.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.springcloud.manager.gateway.admin.entity.po.GatewayRoute;
import com.springcloud.manager.gateway.admin.entity.vo.GatewayRouteVo;
import com.springcloud.manager.gateway.admin.service.IGatewayRouteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
@Slf4j
public class GatewayRouteController {

    @Autowired
    private IGatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutForm", value = "新增网关路由form表单", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public Result add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        log.info("name:", gatewayRoutForm);
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        return Result.success(gatewayRoutService.add(gatewayRout));
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        gatewayRoutService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "gatewayRoutForm", value = "网关路由实体", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Integer id, @Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        GatewayRoute gatewayRout = gatewayRoutForm.toPo(GatewayRoute.class);
        gatewayRout.setId(id);
        gatewayRoutService.update(gatewayRout);
        return Result.success();
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "Integer")
    @GetMapping(value = "/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("get with id:{}", id);
        return Result.success(new GatewayRouteVo(gatewayRoutService.get(id)));
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @GetMapping
    public Result get(@RequestParam String uri) {
        List<GatewayRouteVo> gatewayRoutesVo = gatewayRoutService.query(new GatewayRouteQueryParam(uri)).stream().map(GatewayRouteVo::new).collect(Collectors.toList());
        return Result.success(gatewayRoutesVo.stream().findFirst());
    }

    @ApiOperation(value = "搜索网关路由", notes = "根据条件查询网关路由信息")
    @ApiImplicitParam(name = "gatewayRouteQueryForm", value = "网关路由查询参数", required = true, dataType = "GatewayRouteQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/conditions")
    public Result search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm) {
        List<GatewayRoute> gatewayRoutes = gatewayRoutService.query(gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class));
        List<GatewayRouteVo> gatewayRoutesVo = gatewayRoutes.stream().map(GatewayRouteVo::new).collect(Collectors.toList());
        return Result.success(gatewayRoutesVo);
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping(value = "/overload")
    public Result overload() {
        return Result.success(gatewayRoutService.overload());
    }

}