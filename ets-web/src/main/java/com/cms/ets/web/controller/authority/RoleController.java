package com.cms.ets.web.controller.authority;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.authority.IRoleService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.authority.Role;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Reference
    private IRoleService roleService;
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
    public HandleResult save(@RequestBody Role role) {
        boolean result = roleService.save(role);
        return verifyResp(result);
    }

    @PostMapping("update")
    @ApiOperation("更新")
    public HandleResult update(@RequestBody Role role) {
        boolean result = roleService.updateById(role);
        return verifyResp(result);
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String roleName, String id){
        boolean flag = roleService.checkNameExist(roleName, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = roleService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public HandleResult getById(String id) {
        Role role = roleService.getById(id);
        return success(role);
    }
}
