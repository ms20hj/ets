package com.cms.ets.web.controller.marketing;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.config.IDictionaryService;
import com.cms.ets.api.marketing.IDiscountService;
import com.cms.ets.api.marketing.ITicketService;
import com.cms.ets.api.marketing.ITouristService;
import com.cms.ets.api.marketing.ITravelAgencyService;
import com.cms.ets.common.constant.DictionaryConstant;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.config.Dictionary;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.Tourist;
import com.cms.ets.model.mysql.marketing.TravelAgency;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠配置表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@RestController
@RequestMapping("discount")
public class DiscountController extends BaseController {

    @Reference
    private IDiscountService discountService;
    @Reference
    private ITouristService touristService;
    @Reference
    private ITicketService ticketService;
    @Reference
    private IDictionaryService dictionaryService;
    @Reference
    private ITravelAgencyService travelAgencyService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<Discount> page, String name) {
        IPage<Discount> iPage = discountService.page(page, name);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = Discount.MODULE_NAME, menuName = Discount.MENU_NAME)
    public HandleResult save(@RequestBody Discount discount) {
        discountService.save(discount);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = Discount.MODULE_NAME, menuName = Discount.MENU_NAME)
    public HandleResult update(@RequestBody Discount discount) {
        discountService.updateById(discount);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = discountService.checkNameExist(name, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = Discount.MODULE_NAME, menuName = Discount.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = discountService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据ID查询")
    public HandleResult getById(String id){
        Discount discount = discountService.getById(id);
        return success(discount);
    }

    @GetMapping("getSelectList")
    @ApiOperation("查询选项参数")
    public HandleResult getSelectList(){
        List<Tourist> touristList = touristService.list();
        List<Ticket> ticketList = ticketService.getSimpleList();
        List<Dictionary> discountWayList = dictionaryService.getChildrenByParentPrefix("discountWay");
        List<TravelAgency> travelAgencyList = travelAgencyService.getSimpleLeafList();
        return success(ImmutableMap.of("touristList", touristList, "ticketList", ticketList,
                "discountWayList", discountWayList, "travelAgencyList", travelAgencyList));
    }

}
