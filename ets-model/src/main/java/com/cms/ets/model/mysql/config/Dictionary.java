package com.cms.ets.model.mysql.config;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author cms
 * @since 2019-12-12
 */
@TableName("t_dictionary")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    @TableField("dict_name")
    private String dictName;

    /**
     * 字典值
     */
    @TableField("dict_value")
    private String dictValue;

    /**
     * 上级id
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 状态，0启用，1禁用
     */
    @TableField("status")
    private Integer status;

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
            "dictName=" + dictName +
            ", dictValue=" + dictValue +
            ", parentId=" + parentId +
            ", status=" + status +
        "}";
    }
}
