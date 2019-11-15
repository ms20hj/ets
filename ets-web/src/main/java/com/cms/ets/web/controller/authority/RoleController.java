package com.cms.ets.web.controller.authority;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.authority.IMenuService;
import com.cms.ets.api.authority.IRoleService;
import com.cms.ets.api.authority.IRoleUserService;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.authority.Menu;
import com.cms.ets.model.mysql.authority.Role;
import com.cms.ets.model.mysql.authority.User;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Reference
    private IRoleService roleService;
    @Reference
    private IUserService userService;
    @Reference
    private IRoleUserService roleUserService;
    @Reference
    private IMenuService menuService;
    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<Role> page, String name) {
        IPage<Role> iPage = roleService.page(page, name);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = Role.MODULE_NAME, menuName = Role.MENU_NAME)
    public HandleResult save(@RequestBody Role role) {
        roleService.saveOrUpdateAuth(role);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = Role.MODULE_NAME, menuName = Role.MENU_NAME)
    public HandleResult update(@RequestBody Role role) {
        roleService.saveOrUpdateAuth(role);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String roleName, String id){
        boolean flag = roleService.checkNameExist(roleName, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = Role.MODULE_NAME, menuName = Role.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = roleService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询,不会初始化关联的用户和菜单主键集合")
    public HandleResult getById(String id) {
        Role role = roleService.getById(id);
        return success(role);
    }

    @GetMapping("getAuthDataById")
    @ApiOperation("根据id查询,初始化关联的用户和菜单主键集合")
    public HandleResult getAuthDataById(String id) {
        Role role = roleService.getByIdInitAuthData(id);
        return success(role);
    }

    @GetMapping("getUserAndMenu")
    @ApiOperation("查询系统里面可授权的用户和菜单")
    public HandleResult getUserAndMenu(){
        List<User> userList = userService.getSimpleUserList();
        List<Menu> menuList = menuService.getByCatogory(Menu.CATEGORY_SECOND);
        return success(ImmutableMap.of("userList", userList, "menuList", menuList));
    }
}
