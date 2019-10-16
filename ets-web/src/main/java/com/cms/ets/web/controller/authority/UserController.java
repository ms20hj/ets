package com.cms.ets.web.controller.authority;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.system.User;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 用户controller
 * date 2019年9月25日11:39:49
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
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<User> page, String name) {
        IPage<User> iPage = userService.page(page, name);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public HandleResult save(@RequestBody User user) {
        boolean result = userService.save(user);
        return verifyResp(result);
    }

    @PostMapping("update")
    @ApiOperation("更新")
    public HandleResult update(@RequestBody User user) {
        boolean result = userService.updateById(user);
        return verifyResp(result);
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String userName, String id){
        boolean flag = userService.checkNameExist(userName, id);
        return success(flag);
    }
}
