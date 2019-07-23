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
     * 编号
     */
    @TableField("user_code")
    private String userCode;

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
     * 籍贯
     */
    @TableField("native_place")
    private String nativePlace;

    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 启停状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 删除标识
     */
    @TableField("deleted")
    private Integer deleted;

    @TableField("data_src")
    private Integer dataSrc;

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
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    public Integer getDataSrc() {
        return dataSrc;
    }

    public void setDataSrc(Integer dataSrc) {
        this.dataSrc = dataSrc;
    }

    @Override
    public String getName() {
        return getUserName();
    }

    @Override
    public String toString() {
        return "User{" +
        "realName=" + realName +
        ", userName=" + userName +
        ", userCode=" + userCode +
        ", password=" + password +
        ", gender=" + gender +
        ", phone=" + phone +
        ", nativePlace=" + nativePlace +
        ", idCard=" + idCard +
        ", status=" + status +
        ", deleted=" + deleted +
        ", dataSrc=" + dataSrc +
        "}";
    }
}
