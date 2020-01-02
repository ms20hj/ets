package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 账户门票关联表
 * </p>
 *
 * @author cms
 * @since 2020-01-02
 */
@TableName("t_user_ticket")
public class UserTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 销售窗口id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 窗口id
     */
    @TableField("ticket_id")
    private String ticketId;

    public UserTicket() {
    }

    public UserTicket(String userId, String ticketId) {
        this.userId = userId;
        this.ticketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "UserTicket{" +
            "id=" + id +
            ", userId=" + userId +
            ", ticketId=" + ticketId +
        "}";
    }
}
