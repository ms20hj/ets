package com.cms.ets.model.mysql.marketing;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 门票信息表
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@TableName("t_ticket")
public class Ticket extends BaseEntity {

    private static final long serialVersionUID = 1L;
    public static final String MODULE_NAME = "营销管理";

    public static final String MENU_NAME = "门票管理";
    /**
     * 门票介质
     */
    public static final String TICKET_PHYSICAL = "physical";
    /**
     * 门票期限单位
     * @type {string}
     */
    public static final String TICKET_DEADLINEUNIT = "deadlineUnit";
    /**
     * 出票方式
     * @type {string}
     */
    public static final String TICKET_PRINTMETHOD = "printMethod";
    /**
     * 打印模板
     * @type {string}
     */
    public static final String TICKET_PRINTTEMPLATE = "printTemplate";

    /**
     * 票名称
     */
    @TableField("ticket_name")
    private String ticketName;

    /**
     * 所属景区
     */
    @TableField("scenic_spot_id")
    private String scenicSpotId;

    /**
     * 门票类型id
     */
    @TableField("ticket_category_id")
    private String ticketCategoryId;

    /**
     * 线下窗口售价
     */
    @TableField("sale_price")
    private BigDecimal salePrice;

    /**
     * 票面打印价
     */
    @TableField("print_price")
    private double printPrice;

    /**
     * 网络售价
     */
    @TableField("network_price")
    private double networkPrice;

    /**
     * 门票介质，0：打印条码票，1：ic卡票，2：身份证票
     */
    @TableField("physical")
    private int physical;

    /**
     * 门票有效期限
     */
    @TableField("deadline")
    private int deadline;

    /**
     * 门票期限单位：天D、月M、年Y
     */
    @TableField("deadline_unit")
    private String deadlineUnit;

    /**
     * 出票方式; 0：一人一票，1：一票多人
     */
    @TableField("print_method")
    private int printMethod;

    /**
     * 参售开始日期
     */
    @TableField("begin_date")
    private Date beginDate;

    /**
     * 参售结束日期
     */
    @TableField("end_date")
    private Date endDate;

    /**
     * 状态，0启用，1禁用
     */
    @TableField("status")
    private int status;

    /**
     * 打印模板
     */
    @TableField("print_template")
    private String printTemplate;

    /**
     * 排序
     */
    @TableField("sort_num")
    private int sortNum;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(String scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public String getTicketCategoryId() {
        return ticketCategoryId;
    }

    public void setTicketCategoryId(String ticketCategoryId) {
        this.ticketCategoryId = ticketCategoryId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public double getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(double printPrice) {
        this.printPrice = printPrice;
    }

    public double getNetworkPrice() {
        return networkPrice;
    }

    public void setNetworkPrice(double networkPrice) {
        this.networkPrice = networkPrice;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public String getDeadlineUnit() {
        return deadlineUnit;
    }

    public void setDeadlineUnit(String deadlineUnit) {
        this.deadlineUnit = deadlineUnit;
    }

    public int getPrintMethod() {
        return printMethod;
    }

    public void setPrintMethod(int printMethod) {
        this.printMethod = printMethod;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrintTemplate() {
        return printTemplate;
    }

    public void setPrintTemplate(String printTemplate) {
        this.printTemplate = printTemplate;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ticket{" +
            "ticketName=" + ticketName +
            ", scenicSpotId=" + scenicSpotId +
            ", ticketCategoryId=" + ticketCategoryId +
            ", salePrice=" + salePrice +
            ", printPrice=" + printPrice +
            ", networkPrice=" + networkPrice +
            ", physical=" + physical +
            ", deadline=" + deadline +
            ", deadlineUnit=" + deadlineUnit +
            ", printMethod=" + printMethod +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
            ", status=" + status +
            ", printTemplate=" + printTemplate +
            ", sortNum=" + sortNum +
            ", description=" + description +
        "}";
    }
}
