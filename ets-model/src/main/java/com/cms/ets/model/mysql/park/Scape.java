package com.cms.ets.model.mysql.park;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 景点信息表
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
@TableName("t_scape")
public class Scape extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 景点名称
     */
    @TableField("scape_name")
    private String scapeName;

    /**
     * 所属景区
     */
    @TableField("scenic_spot_id")
    private String scenicSpotId;
    /**
     * 所属景区关联对象
     */
    @TableField(exist = false)
    private ScenicSpot scenicSpot;
    /**
     * 描述
     */
    @TableField("description")
    private String description;

    public String getScapeName() {
        return scapeName;
    }

    public void setScapeName(String scapeName) {
        this.scapeName = scapeName;
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
        return "Scape{" +
            "scapeName=" + scapeName +
            ", scenicSpotId=" + scenicSpotId +
            ", description=" + description +
        "}";
    }
}
