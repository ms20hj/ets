package com.cms.ets.web.controller.marketing;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.marketing.ITicketService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门票信息表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@RestController
@RequestMapping("ticket")
public class TicketController extends BaseController {

    @Reference
    private ITicketService ticketService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<Ticket> page, String name, String parentId) {
        IPage<Ticket> iPage = ticketService.page(page, name, parentId);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult save(@RequestBody Ticket ticket) {
        ticketService.save(ticket);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult update(@RequestBody Ticket ticket) {
        ticketService.updateById(ticket);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = ticketService.checkNameExist(name, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = ticketService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public HandleResult getById(String id) {
        Ticket ticket = ticketService.getById(id);
        return success(ticket);
    }

    /**
     * 获取门票下拉框初始化值
     * @return HandleResult
     * @date 2019年12月19日17:50:16
     * @author ChenMingsen
     */
    @GetMapping("getTicketSelectParams")
    public HandleResult getTicketSelectParams(){
        Map<String, Object> params = ticketService.getTicketSelectParams();
        return success(params);
    }
}
