package com.cms.ets.web.controller.marketing;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.marketing.ITicketScapeService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.marketing.TicketScape;
import com.cms.ets.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 门票景点控制器
 * @date 2019年12月27日10:11:41
 * @author cms
 */
@RestController
@RequestMapping("ticketScape")
public class TicketScapeController extends BaseController {

    @Reference
    private ITicketScapeService ticketScapeService;

    @GetMapping("getByTicketId")
    public HandleResult getByTicketId(String ticketId){
        List<TicketScape> list = ticketScapeService.getByTicketId(ticketId);
        return success(list);
    }
}
