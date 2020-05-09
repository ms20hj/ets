package com.cms.ets.web.controller.order;


import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.config.IDictionaryService;
import com.cms.ets.api.marketing.IDiscountService;
import com.cms.ets.api.marketing.ITicketService;
import com.cms.ets.api.marketing.ITouristService;
import com.cms.ets.api.order.ISaleOrderService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.marketing.Tourist;
import com.cms.ets.model.mysql.order.SaleOrder;
import com.cms.ets.model.mysql.order.SaleOrderType;
import com.cms.ets.web.controller.BaseController;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售订表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@RestController
@RequestMapping("saleOrder")
public class SaleOrderController extends BaseController {

    @Reference
    private ISaleOrderService saleOrderService;
    @Reference
    private ITouristService touristService;
    @Reference
    private ITicketService ticketService;
    @Reference
    private IDiscountService discountService;

    /**
     * 创建订单
     * @param size 订单类别大小
     * @return HandleResult
     */
    @GetMapping("createOrder")
    public HandleResult createOrder(String size){
        int length = StringUtils.isBlank(size) ? 1 : Integer.parseInt(size);
        SaleOrder saleOrder = saleOrderService.createOrder(length, getCurrentUser());
        return success(saleOrder);
    }

    /**
     * 获取空的SaleOrderType
     * @return HandleResult
     */
    @GetMapping("getEmptySaleOrderType")
    public HandleResult getEmptySaleOrderType(){
        SaleOrderType saleOrderType = new SaleOrderType(1, 1);
        saleOrderType.setTouristName("");
        saleOrderType.setTicketName("");
        return success(saleOrderType);
    }


    /**
     * 获取销售基础数据，（游客类型，可售门票）
     * @date 2020年4月9日10:45:08
     * @return HandleResult
     */
    @GetMapping("getBaseSaleData")
    public HandleResult getBaseSaleData() {
        List<Tourist> touristList = touristService.list();
        List<Ticket> ticketList = ticketService.getByUserId(getCurrentUser().getId());
        List<Discount> discountList = discountService.list();
        Map<String, Object> map = ImmutableMap.of("touristList", touristList, "ticketList", ticketList, "discountList", discountList);
        return success(map);
    }

}
