package com.cms.ets.model.mongo;

import com.cms.ets.model.mysql.authority.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

/**
 * 日志基础对象
 * @date 2019年11月15日11:43:09
 * @author ChenMingsen
 */
public abstract class BaseLogEntity implements Serializable {
    private static final long serialVersionUID = 1956786888637253872L;

    @Id
    protected String id;

    @Indexed
    protected String createTime;

    @Indexed
    protected String userId;

    protected String userName;

    protected String realName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public abstract void setUser(User user);
}
