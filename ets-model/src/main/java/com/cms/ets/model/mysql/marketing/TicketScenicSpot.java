package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 门票景区关联表
 * </p>
 *
 * @author cms
 * @since 2019-12-26
 */
@TableName("t_ticket_scenic_spot")
public class TicketScenicSpot implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 景区id
     */
    @TableField("scenic_spot_id")
    private String scenicSpotId;

    /**
     * 门票id
     */
    @TableField("ticket_id")
    private String ticketId;

    public TicketScenicSpot() {
    }

    public TicketScenicSpot(String scenicSpotId, String ticketId) {
        this.scenicSpotId = scenicSpotId;
        this.ticketId = ticketId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(String scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "TicketScenicSpot{" +
            "id=" + id +
            ", scenicSpotId=" + scenicSpotId +
            ", ticketId=" + ticketId +
        "}";
    }
}
