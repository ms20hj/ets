package com.cms.ets.web.controller.marketing;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.marketing.ITicketCategoryService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.TicketCategory;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 门票分类表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@RestController
@RequestMapping("ticketCategory")
public class TicketCategoryController extends BaseController {

    @Reference
    private ITicketCategoryService ticketCategoryService;

    @GetMapping("listTree")
    @ApiOperation("列表查询")
    public HandleResult listTree() {
        List<TicketCategory> list = ticketCategoryService.listTree();
        return success(list);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult save(@RequestBody TicketCategory ticketCategory) {
        ticketCategoryService.save(ticketCategory);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult update(@RequestBody TicketCategory ticketCategory) {
        ticketCategoryService.updateById(ticketCategory);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = ticketCategoryService.checkNameExist(name, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = ticketCategoryService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public HandleResult getById(String id) {
        TicketCategory ticketCategory = ticketCategoryService.getById(id);
        return success(ticketCategory);
    }
}
