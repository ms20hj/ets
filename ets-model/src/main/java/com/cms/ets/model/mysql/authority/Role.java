package com.cms.ets.model.mysql.authority;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cms.ets.model.mysql.BaseEntity;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author cms
 * @since 2019-10-18
 */
@TableName("t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色描述
     */
    @TableField("description")
    private String description;

    /**
     * 状态,0启用，1禁用
     */
    @TableField("status")
    private Integer status;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
            "roleName=" + roleName +
            ", description=" + description +
            ", status=" + status +
        "}";
    }
}
