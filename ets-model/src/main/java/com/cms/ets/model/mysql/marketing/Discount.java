package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;

import java.util.List;

/**
 * <p>
 * 优惠配置表
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@TableName("t_discount")
public class Discount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String MODULE_NAME = "营销管理";

    public static final String MENU_NAME = "优惠管理";
    /**
     * 名称
     */
    @TableField("disc_name")
    private String discName;

    /**
     * 人数限制,0表示无人数限制
     */
    @TableField("limit_count")
    private boolean limitCount;

    /**
     * 人数限制区间开始值
     */
    @TableField("limit_begin")
    private int limitBegin;

    /**
     * 人数限制区间结束值
     */
    @TableField("limit_end")
    private int limitEnd;

    /**
     * 打折方式，0：打折（如：8折、九折），1：减免（如：减10/人，减15/人），2：按照优惠金额销售（如：直接￥50）
     */
    @TableField("discount_way")
    private int discountWay;

    /**
     * 优惠的比例
     */
    @TableField("discount_scale")
    private double discountScale;

    /**
     * 关联游客集合
     */
    @TableField(exist = false)
    private List<Tourist> touristList;

    /**
     * 关联游客ID集合
     */
    @TableField(exist = false)
    private List<String> touristIds;

    /**
     * 关联门票集合
     */
    @TableField(exist = false)
    private List<Ticket> ticketList;

    /**
     * 关联门票id集合
     */
    @TableField(exist = false)
    private List<String> ticketIds;

    /**
     * 关联旅行社集合
     */
    @TableField(exist = false)
    private List<TravelAgency> travelAgencyList;

    /**
     * 关联旅行社id集合
     */
    @TableField(exist = false)
    private List<String> travelAgencyIds;

    public String getDiscName() {
        return discName;
    }

    public void setDiscName(String discName) {
        this.discName = discName;
    }

    public boolean isLimitCount() {
        return limitCount;
    }

    public void setLimitCount(boolean limitCount) {
        this.limitCount = limitCount;
    }

    public int getLimitBegin() {
        return limitBegin;
    }

    public void setLimitBegin(int limitBegin) {
        this.limitBegin = limitBegin;
    }
    public int getLimitEnd() {
        return limitEnd;
    }

    public void setLimitEnd(int limitEnd) {
        this.limitEnd = limitEnd;
    }
    public int getDiscountWay() {
        return discountWay;
    }

    public void setDiscountWay(int discountWay) {
        this.discountWay = discountWay;
    }
    public double getDiscountScale() {
        return discountScale;
    }

    public void setDiscountScale(double discountScale) {
        this.discountScale = discountScale;
    }

    public List<Tourist> getTouristList() {
        return touristList;
    }

    public void setTouristList(List<Tourist> touristList) {
        this.touristList = touristList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<String> getTouristIds() {
        return touristIds;
    }

    public void setTouristIds(List<String> touristIds) {
        this.touristIds = touristIds;
    }

    public List<String> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<String> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public List<TravelAgency> getTravelAgencyList() {
        return travelAgencyList;
    }

    public void setTravelAgencyList(List<TravelAgency> travelAgencyList) {
        this.travelAgencyList = travelAgencyList;
    }

    public List<String> getTravelAgencyIds() {
        return travelAgencyIds;
    }

    public void setTravelAgencyIds(List<String> travelAgencyIds) {
        this.travelAgencyIds = travelAgencyIds;
    }

    @Override
    public String toString() {
        return "Discount{" +
            "discName=" + discName +
            ", limitCount=" + limitCount +
            ", limitBegin=" + limitBegin +
            ", limitEnd=" + limitEnd +
            ", discountWay=" + discountWay +
            ", discountScale=" + discountScale +
        "}";
    }
}
