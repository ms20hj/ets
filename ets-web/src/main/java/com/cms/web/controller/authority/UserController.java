package com.cms.web.controller.authority;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.system.User;
import com.cms.web.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理-用户管理
 * controller
 * @date 2019年9月9日16:06:36
 * @author ChenMingsen
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Reference
    private IUserService userService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @date 2019年9月9日16:11:48
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<User> page, String name) {
        IPage<User> iPage = userService.page(page, name);
        return seccess(iPage);
    }

}
