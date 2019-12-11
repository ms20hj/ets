package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

/**
 * <p>
 * 门票分类表
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@TableName("t_ticket_category")
public class TicketCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 根节点id
     */
    public static final String ROOT_ID = "ROOT";

    /**
     * 名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 排序
     */
    @TableField("sort_num")
    private Integer sortNum;

    @TableField(exist = false)
    private List<TicketCategory> children;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortNum() {
        return sortNum;
    }
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public List<TicketCategory> getChildren() {
        return children;
    }

    public void setChildren(List<TicketCategory> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TicketCategory{" +
            "categoryName=" + categoryName +
            ", description=" + description +
            ", sortNum=" + sortNum +
        "}";
    }
}
