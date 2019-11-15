package com.cms.ets.model.mysql.park;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 景区信息表
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
@TableName("t_scenic_spot")
public class ScenicSpot extends BaseEntity {

    private static final long serialVersionUID = 1L;
    public static final String MODULE_NAME = "园区管理";

    public static final String MENU_NAME = "景区点管理";
    /**
     * 景区名称
     */
    @TableField("spot_name")
    private String spotName;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ScenicSpot{" +
            "spotName=" + spotName +
            ", description=" + description +
        "}";
    }
}
