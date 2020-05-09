package com.cms.ets.model.mysql.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;

import java.util.List;

/**
 * <p>
 * 销售订表
 * </p>
 *
 * @author cms
 * @since 2020-04-08
 */
@TableName("t_sale_order")
public class SaleOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @TableField("customer_id")
    private String customerId;

    /**
     * 客户名称
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 支付方式
     */
    @TableField("pay_method")
    private String payMethod;

    /**
     * 客源地id
     */
    @TableField("source_area_id")
    private String sourceAreaId;

    /**
     * 客源地名称
     */
    @TableField("source_area_name")
    private String sourceAreaName;

    /**
     * 销售站点id
     */
    @TableField("sale_site_id")
    private String saleSiteId;

    /**
     * 销售站点名称
     */
    @TableField("sale_site_name")
    private String saleSiteName;

    /**
     * 销售窗口id
     */
    @TableField("sale_window_id")
    private String saleWindowId;

    /**
     * 销售窗口名称
     */
    @TableField("sale_window_name")
    private String saleWindowName;

    /**
     * 销售名称
     */
    @TableField("create_name")
    private String createName;

    /**
     * 订单状态枚举
     */
    @TableField("state")
    private int state;

    /**
     * 总金额
     */
    @TableField("total_amount")
    private double totalAmount;

    /**
     * 总票数
     */
    @TableField("ticket_count")
    private int ticketCount;

    /**
     * 总人数
     */
    @TableField("person_count")
    private int personCount;

    /**
     * 订单类别集合
     */
    @TableField(exist = false)
    private List<SaleOrderType> saleOrderTypeList;

    /**
     * 订单明细集合
     */
    @TableField(exist = false)
    private List<SaleOrderItem> saleOrderItemList;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
    public String getSourceAreaId() {
        return sourceAreaId;
    }

    public void setSourceAreaId(String sourceAreaId) {
        this.sourceAreaId = sourceAreaId;
    }
    public String getSourceAreaName() {
        return sourceAreaName;
    }

    public void setSourceAreaName(String sourceAreaName) {
        this.sourceAreaName = sourceAreaName;
    }
    public String getSaleSiteId() {
        return saleSiteId;
    }

    public void setSaleSiteId(String saleSiteId) {
        this.saleSiteId = saleSiteId;
    }
    public String getSaleSiteName() {
        return saleSiteName;
    }

    public void setSaleSiteName(String saleSiteName) {
        this.saleSiteName = saleSiteName;
    }
    public String getSaleWindowId() {
        return saleWindowId;
    }

    public void setSaleWindowId(String saleWindowId) {
        this.saleWindowId = saleWindowId;
    }
    public String getSaleWindowName() {
        return saleWindowName;
    }

    public void setSaleWindowName(String saleWindowName) {
        this.saleWindowName = saleWindowName;
    }
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }
    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public List<SaleOrderType> getSaleOrderTypeList() {
        return saleOrderTypeList;
    }

    public void setSaleOrderTypeList(List<SaleOrderType> saleOrderTypeList) {
        this.saleOrderTypeList = saleOrderTypeList;
    }

    public List<SaleOrderItem> getSaleOrderItemList() {
        return saleOrderItemList;
    }

    public void setSaleOrderItemList(List<SaleOrderItem> saleOrderItemList) {
        this.saleOrderItemList = saleOrderItemList;
    }

    @Override
    public String toString() {
        return "SaleOrder{" +
            "customerId=" + customerId +
            ", customerName=" + customerName +
            ", payMethod=" + payMethod +
            ", sourceAreaId=" + sourceAreaId +
            ", sourceAreaName=" + sourceAreaName +
            ", saleSiteId=" + saleSiteId +
            ", saleSiteName=" + saleSiteName +
            ", saleWindowId=" + saleWindowId +
            ", saleWindowName=" + saleWindowName +
            ", createName=" + createName +
            ", state=" + state +
            ", totalAmount=" + totalAmount +
            ", ticketCount=" + ticketCount +
            ", personCount=" + personCount +
        "}";
    }
}
