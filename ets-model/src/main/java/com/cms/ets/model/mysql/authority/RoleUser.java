package com.cms.ets.model.mysql.authority;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("t_role_user")
public class RoleUser implements Serializable {
    private static final long serialVersionUID = 4895335838614552717L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("user_id")
    private String userId;

    public RoleUser() {
    }

    public RoleUser(String roleId, String userId) {
        this.roleId = roleId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
