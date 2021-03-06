package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 优惠配置客户关联表
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@TableName("t_discount_travel_agency")
public class DiscountTravelAgency implements Serializable {

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
     * 客户id
     */
    @TableField("travel_agency_id")
    private String travelAgencyId;

    public DiscountTravelAgency() {
    }

    public DiscountTravelAgency(String discountId, String travelAgencyId) {
        this.discountId = discountId;
        this.travelAgencyId = travelAgencyId;
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

    public String getTravelAgencyId() {
        return travelAgencyId;
    }

    public void setTravelAgencyId(String travelAgencyId) {
        this.travelAgencyId = travelAgencyId;
    }

    @Override
    public String toString() {
        return "DiscountTravelAgency{" +
            "id=" + id +
            ", discountId=" + discountId +
            ", travelAgencyId=" + travelAgencyId +
        "}";
    }
}
