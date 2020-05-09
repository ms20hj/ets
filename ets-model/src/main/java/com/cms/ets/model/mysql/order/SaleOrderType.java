package com.cms.ets.model.mysql.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.cms.ets.model.mysql.marketing.Discount;
import com.cms.ets.model.mysql.marketing.Ticket;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 销售订单类型
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@TableName("t_sale_order_type")
public class SaleOrderType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联订单号
     */
    @TableField("sale_order_id")
    private String saleOrderId;

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
     * 门票介质，0：打印条码票，1：ic卡票，2：身份证票
     */
    @TableField("ticket_physical")
    private int ticketPhysical;

    /**
     * 票面打印价
     */
    @TableField("ticket_print_price")
    private double ticketPrintPrice;

    /**
     * 游客id
     */
    @TableField("tourist_id")
    private String touristId;

    /**
     * 门票对象
     */
    @TableField(exist = false)
    private Ticket ticket;
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
     * 打折方式对象
     */
    @TableField(exist = false)
    private Discount discount;

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

    public SaleOrderType() {
    }

    public SaleOrderType(int personCount, int ticketCount) {
        this.personCount = personCount;
        this.ticketCount = ticketCount;
    }

    @TableField(exist = false)
    private List<SaleOrderItem> saleOrderItemList;

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
    public int getTicketPhysical() {
        return ticketPhysical;
    }

    public void setTicketPhysical(int ticketPhysical) {
        this.ticketPhysical = ticketPhysical;
    }
    public double getTicketPrintPrice() {
        return ticketPrintPrice;
    }

    public void setTicketPrintPrice(double ticketPrintPrice) {
        this.ticketPrintPrice = ticketPrintPrice;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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

    public List<SaleOrderItem> getSaleOrderItemList() {
        return saleOrderItemList;
    }

    public void setSaleOrderItemList(List<SaleOrderItem> saleOrderItemList) {
        this.saleOrderItemList = saleOrderItemList;
    }

    @Override
    public String toString() {
        return "SaleOrderType{" +
            "ticketId=" + ticketId +
            ", ticketName=" + ticketName +
            ", ticketPhysical=" + ticketPhysical +
            ", ticketPrintPrice=" + ticketPrintPrice +
            ", touristId=" + touristId +
            ", touristName=" + touristName +
            ", discountId=" + discountId +
            ", discountName=" + discountName +
            ", price=" + price +
            ", personCount=" + personCount +
            ", ticketCount=" + ticketCount +
            ", amount=" + amount +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
        "}";
    }
}
