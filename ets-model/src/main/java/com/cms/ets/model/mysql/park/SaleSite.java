package com.cms.ets.model.mysql.park;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 销售站点信息表
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
@TableName("t_sale_site")
public class SaleSite extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 站点名称
     */
    @TableField("site_name")
    private String siteName;

    /**
     * 所属景区id
     */
    @TableField("scenic_spot_id")
    private String scenicSpotId;

    /**
     * 描述
     */
    @TableField("description")
    private String description;
    /**
     * 关联景区
     */
    @TableField(exist = false)
    private ScenicSpot scenicSpot;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(String scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScenicSpot getScenicSpot() {
        return scenicSpot;
    }

    public void setScenicSpot(ScenicSpot scenicSpot) {
        this.scenicSpot = scenicSpot;
    }

    @Override
    public String toString() {
        return "SaleSite{" +
            "siteName=" + siteName +
            ", scenicSpotId=" + scenicSpotId +
            ", description=" + description +
        "}";
    }
}
