package com.cms.ets.web.controller.marketing;


import cn.hutool.json.JSONObject;
import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IUserService;
import com.cms.ets.api.marketing.IUserTicketService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.authority.User;
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

    /**
     * 查询用户和关联此票类的数据
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
    public HandleResult authTicketUser(@RequestBody JSONObject jsonObject) {
        String ticketId = jsonObject.getStr("ticketId");
        List<String> userIds = jsonObject.getJSONArray("userIds").toList(String.class);
        userTicketService.authTicketUser(ticketId, userIds);
        return success();
    }
}
