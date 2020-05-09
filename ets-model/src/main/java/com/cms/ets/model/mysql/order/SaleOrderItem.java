package com.cms.ets.model.mysql.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;

import java.util.Date;

/**
 * <p>
 * 销售订单明细项表
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@TableName("t_sale_order_item")
public class SaleOrderItem extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联订单号
     */
    @TableField("sale_order_id")
    private String saleOrderId;

    /**
     * 关联订单类别id
     */
    @TableField("sale_order_type_id")
    private String saleOrderTypeId;

    /**
     * 票类id
     */
    @TableField("ticket_id")
    private String ticketId;

    /**
     * 票名称
     */
    @TableField("ticket_name")
    private String ticketName;

    /**
     * 门票打印价格
     */
    @TableField("ticket_print_price")
    private double ticketPrintPrice;

    /**
     * 游客id
     */
    @TableField("tourist_id")
    private String touristId;

    /**
     * 游客名称
     */
    @TableField("tourist_name")
    private String touristName;

    /**
     * 折扣id
     */
    @TableField("discount_id")
    private String discountId;

    /**
     * 折扣名称
     */
    @TableField("discount_name")
    private String discountName;

    /**
     * 条码号
     */
    @TableField("bar_code")
    private String barCode;

    /**
     * 单价
     */
    @TableField("price")
    private double price;

    /**
     * 人数
     */
    @TableField("person_count")
    private int personCount;

    /**
     * 票数
     */
    @TableField("ticket_count")
    private int ticketCount;

    /**
     * 金额小计
     */
    @TableField("amount")
    private double amount;

    /**
     * 有效期开始时间
     */
    @TableField("begin_date")
    private Date beginDate;

    /**
     * 有效期结束时间
     */
    @TableField("end_date")
    private Date endDate;

    public String getSaleOrderTypeId() {
        return saleOrderTypeId;
    }

    public void setSaleOrderTypeId(String saleOrderTypeId) {
        this.saleOrderTypeId = saleOrderTypeId;
    }

    public String getSaleOrderId() {
        return saleOrderId;
    }

    public void setSaleOrderId(String saleOrderId) {
        this.saleOrderId = saleOrderId;
    }
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }
    public double getTicketPrintPrice() {
        return ticketPrintPrice;
    }

    public void setTicketPrintPrice(double ticketPrintPrice) {
        this.ticketPrintPrice = ticketPrintPrice;
    }
    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }
    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }
    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }
    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }
    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SaleOrderItem{" +
            "saleOrderId=" + saleOrderId +
            ", ticketId=" + ticketId +
            ", ticketName=" + ticketName +
            ", ticketPrintPrice=" + ticketPrintPrice +
            ", touristId=" + touristId +
            ", touristName=" + touristName +
            ", discountId=" + discountId +
            ", discountName=" + discountName +
            ", barCode=" + barCode +
            ", price=" + price +
            ", personCount=" + personCount +
            ", ticketCount=" + ticketCount +
            ", amount=" + amount +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
        "}";
    }
}
