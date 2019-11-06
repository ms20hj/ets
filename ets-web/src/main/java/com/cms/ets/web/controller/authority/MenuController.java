package com.cms.ets.web.controller.authority;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IMenuService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.authority.Menu;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-10-23
 */
@RestController
@RequestMapping("/system/menu")
@Api("菜单")
public class MenuController extends BaseController {

    @Reference
    private IMenuService menuService;

    @PostMapping("save")
    @ApiOperation("保存菜单")
    public HandleResult save(@RequestBody Menu menu){
        boolean flag = menuService.save(menu);
        return verifyResp(flag);
    }
}
