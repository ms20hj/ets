package com.cms.ets.web.controller.park;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.park.ISaleWindowService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.park.SaleWindow;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 销售窗口信息表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
@RestController
@RequestMapping("saleWindow")
public class SaleWindowController extends BaseController {

    @Reference
    private ISaleWindowService saleWindowService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<SaleWindow> page, String name) {
        IPage<SaleWindow> iPage = saleWindowService.page(page, name);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = SaleWindow.MODULE_NAME, menuName = SaleWindow.MENU_NAME)
    public HandleResult save(@RequestBody SaleWindow window) {
        saleWindowService.save(window);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = SaleWindow.MODULE_NAME, menuName = SaleWindow.MENU_NAME)
    public HandleResult update(@RequestBody SaleWindow window) {
        saleWindowService.updateById(window);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = saleWindowService.checkNameExist(name, id);
        return success(flag);
    }

    @GetMapping("checkMacExist")
    @ApiOperation("检验用户名Mac是否存在")
    public HandleResult checkMacExist(String mac, String id){
        boolean flag = saleWindowService.checkMacExist(mac, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = SaleWindow.MODULE_NAME, menuName = SaleWindow.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = saleWindowService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public HandleResult getById(String id) {
        SaleWindow window = saleWindowService.getById(id);
        return success(window);
    }
}
