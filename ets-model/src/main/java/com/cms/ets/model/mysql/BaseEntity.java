package com.cms.ets.model.mysql;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础通用抽象实体类
 * @date 2019年7月22日14:20:48
 * @author ChenMingsen
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -928229009341354830L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    protected String id;

    @TableField(value = "create_id",fill = FieldFill.INSERT)
    protected String createId;

    @TableField(value = "update_id",fill = FieldFill.UPDATE)
    protected String updateId;


    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    protected Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    protected Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
