package com.cms.ets.model.mysql.marketing;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

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
