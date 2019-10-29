package com.cms.ets.model.mysql.marketing;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

/**
 * <p>
 * 旅行社信息表
 * </p>
 *
 * @author cms
 * @since 2019-10-29
 */
@TableName("t_travel_agency")
public class TravelAgency extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 根节点id
     */
    public static final String ROOT_ID = "ROOT";
    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 联系方式
     */
    @TableField("phone")
    private String phone;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 联系地址
     */
    @TableField("address")
    private String address;

    /**
     * 状态 0：启用，1：禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 上级
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 等级
     */
    @TableField("level")
    private int level;

    @TableField(exist = false)
    private List<TravelAgency> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<TravelAgency> getChildren() {
        return children;
    }

    public void setChildren(List<TravelAgency> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TravelAgency{" +
            "name=" + name +
            ", phone=" + phone +
            ", contact=" + contact +
            ", address=" + address +
            ", status=" + status +
            ", parentId=" + parentId +
            ", level=" + level +
        "}";
    }
}
