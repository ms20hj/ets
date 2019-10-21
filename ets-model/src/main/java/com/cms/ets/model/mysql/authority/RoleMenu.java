package com.cms.ets.model.mysql.authority;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1198255802436388364L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableField("role_id")
    private String roleId;

    @TableField("menu_id")
    private String menuId;

    public RoleMenu() {
    }

    public RoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
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

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
