package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 优惠配置游客类型关联表
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@TableName("t_discount_tourist")
public class DiscountTourist implements Serializable {

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
     * 游客类型id
     */
    @TableField("tourist_id")
    private String touristId;

    public DiscountTourist() {
    }

    public DiscountTourist(String discountId, String touristId) {
        this.discountId = discountId;
        this.touristId = touristId;
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
    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    @Override
    public String toString() {
        return "DiscountTourist{" +
            "id=" + id +
            ", discountId=" + discountId +
            ", touristId=" + touristId +
        "}";
    }
}
