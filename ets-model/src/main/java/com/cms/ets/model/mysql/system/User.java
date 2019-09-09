package com.cms.ets.model.mysql.system;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cms.ets.model.mysql.BaseEntity;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author cms
 * @since 2019-07-22
 */
@TableName("t_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 账户
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 启停状态
     */
    @TableField("status")
    private Integer status;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
