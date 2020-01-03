package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 优惠配置门票关联表
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@TableName("t_discount_ticket")
public class DiscountTicket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 优惠配置id
     */
    @TableField("discount_id")
    private String discountId;

    /**
     * 窗口id
     */
    @TableField("ticket_id")
    private String ticketId;

    public DiscountTicket() {
    }

    public DiscountTicket(String discountId, String ticketId) {
        this.discountId = discountId;
        this.ticketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "DiscountTicket{" +
            "id=" + id +
            ", discountId=" + discountId +
            ", ticketId=" + ticketId +
        "}";
    }
}
