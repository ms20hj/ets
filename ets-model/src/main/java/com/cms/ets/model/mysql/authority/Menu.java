package com.cms.ets.model.mysql.authority;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author cms
 * @since 2019-10-23
 */
@TableName("t_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String CATEGORY_FIRST = "一级";
    public static final String CATEGORY_SECOND = "二级";
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 菜单url
     */
    @TableField("url")
    private String url;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 菜单类型
     */
    @TableField("category")
    private String category;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 所属父类ID
     */
    @TableField("parent_id")
    private String parentId;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Menu{" +
            "menuName=" + menuName +
            ", icon=" + icon +
            ", url=" + url +
            ", sort=" + sort +
            ", category=" + category +
            ", status=" + status +
            ", parentId=" + parentId +
        "}";
    }
}
