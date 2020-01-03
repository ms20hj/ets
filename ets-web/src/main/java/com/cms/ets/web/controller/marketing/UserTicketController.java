package com.cms.ets.web.controller.marketing;


import cn.hutool.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.api.marketing.ITicketService;
import com.cms.ets.api.marketing.IUserTicketService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.authority.User;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 账户门票关联表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2020-01-02
 */
@RestController
@RequestMapping("userTicket")
public class UserTicketController extends BaseController {

    @Reference
    private IUserTicketService userTicketService;
    @Reference
    private IUserService userService;
    @Reference
    private ITicketService ticketService;

    /**
     * 根据ticket id查询用户和关联此票类的数据
     * @param ticketId Ticket ID
     * @return HandleResult
     */
    @GetMapping("getUsersAndChecked")
    public HandleResult getUsersAndChecked(String ticketId){
        List<String> userTicketList = userTicketService.getUserIdByTicketId(ticketId);
        List<User> userList = userService.getSimpleUserList();
        return success(ImmutableMap.of("userTicketList", userTicketList, "userList", userList));
    }

    /**
     * 授权门票用户销售关系
     * @param jsonObject
     * @return HandleResult
     * @date 2020年1月2日15:05:10
     */
    @PostMapping("authTicketUser")
    @OperationLog(action = OperateLogConstant.AUTH, moduleName = Ticket.MODULE_NAME, menuName = Ticket.MENU_NAME)
    public HandleResult authTicketUser(@RequestBody JSONObject jsonObject) {
        String ticketId = jsonObject.getStr("ticketId");
        List<String> userIds = jsonObject.getJSONArray("userIds").toList(String.class);
        userTicketService.authTicketUser(ticketId, userIds);
        return success();
    }

    /**
     * 根据User id 查询关联的门票数据
     * @param userId User id
     * @return HandleResult
     */
    @GetMapping("getTicketsAndChecked")
    public HandleResult getTicketsAndChecked(String userId){
        List<Ticket> ticketList = ticketService.getSimpleList();
        List<String> userTicketList = userTicketService.getTicketIdByUserId(userId);
        return success(ImmutableMap.of("ticketList", ticketList, "userTicketList", userTicketList));
    }

    /**
     * 用户授权门票
     * @param jsonObject
     * @return HandleResult
     * @date 2020年1月3日10:50:56
     */
    @PostMapping("authUserTicket")
    @OperationLog(action = OperateLogConstant.AUTH, moduleName = User.MODULE_NAME, menuName = User.MENU_NAME)
    public HandleResult authUserTicket(@RequestBody JSONObject jsonObject) {
        String userId = jsonObject.getStr("userId");
        List<String> ticketIds = jsonObject.getJSONArray("ticketIds").toList(String.class);
        userTicketService.authUserTicket(userId, ticketIds);
        return success();
    }
}
