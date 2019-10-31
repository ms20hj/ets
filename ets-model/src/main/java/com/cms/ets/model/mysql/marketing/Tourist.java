package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 游客类型表
 * </p>
 *
 * @author cms
 * @since 2019-10-31
 */
@TableName("t_tourist")
public class Tourist extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("tourist_name")
    private String touristName;

    /**
     * 排序
     */
    @TableField("sort_num")
    private Integer sortNum;

    public String getTouristName() {
        return touristName;
    }

    public void setTouristName(String touristName) {
        this.touristName = touristName;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public String toString() {
        return "Tourist{" +
            "touristName=" + touristName +
            ", sortNum=" + sortNum +
        "}";
    }
}
