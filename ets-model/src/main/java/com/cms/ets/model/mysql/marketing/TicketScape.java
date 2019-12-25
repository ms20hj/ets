package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 门票景点可进次数配置
 * </p>
 *
 * @author cms
 * @since 2019-12-25
 */
@TableName("t_ticket_scape")
public class TicketScape extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 票类id
     */
    @TableField("ticket_id")
    private String ticketId;

    /**
     * 所属景区
     */
    @TableField("scenic_spot_id")
    private String scenicSpotId;

    /**
     * 景点id
     */
    @TableField("scape_id")
    private String scapeId;

    /**
     * 景点名称
     */
    @TableField("scape_name")
    private String scapeName;

    /**
     * 总可进次数
     */
    @TableField("all_in")
    private Integer allIn;

    /**
     * 日可进次数
     */
    @TableField("day_in")
    private Integer dayIn;

    public TicketScape() {
    }

    public TicketScape(String ticketId, String scenicSpotId, String scapeId, String scapeName) {
        this.ticketId = ticketId;
        this.scenicSpotId = scenicSpotId;
        this.scapeId = scapeId;
        this.scapeName = scapeName;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    public String getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(String scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }
    public String getScapeId() {
        return scapeId;
    }

    public void setScapeId(String scapeId) {
        this.scapeId = scapeId;
    }
    public String getScapeName() {
        return scapeName;
    }

    public void setScapeName(String scapeName) {
        this.scapeName = scapeName;
    }
    public Integer getAllIn() {
        return allIn;
    }

    public void setAllIn(Integer allIn) {
        this.allIn = allIn;
    }
    public Integer getDayIn() {
        return dayIn;
    }

    public void setDayIn(Integer dayIn) {
        this.dayIn = dayIn;
    }

    @Override
    public String toString() {
        return "TicketScape{" +
            "ticketId=" + ticketId +
            ", scenicSpotId=" + scenicSpotId +
            ", scapeId=" + scapeId +
            ", scapeName=" + scapeName +
            ", allIn=" + allIn +
            ", dayIn=" + dayIn +
        "}";
    }
}
