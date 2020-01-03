package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 优惠配置客源地关联表
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
@TableName("t_discount_source_area")
public class DiscountSourceArea implements Serializable {

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
     * 客源地id
     */
    @TableField("source_area_id")
    private String sourceAreaId;

    public DiscountSourceArea() {
    }

    public DiscountSourceArea(String discountId, String sourceAreaId) {
        this.discountId = discountId;
        this.sourceAreaId = sourceAreaId;
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
    public String getSourceAreaId() {
        return sourceAreaId;
    }

    public void setSourceAreaId(String sourceAreaId) {
        this.sourceAreaId = sourceAreaId;
    }

    @Override
    public String toString() {
        return "DiscountSourceArea{" +
            "id=" + id +
            ", discountId=" + discountId +
            ", sourceAreaId=" + sourceAreaId +
        "}";
    }
}
