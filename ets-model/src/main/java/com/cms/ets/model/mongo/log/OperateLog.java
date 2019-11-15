package com.cms.ets.model.mongo.log;

import com.cms.ets.model.mongo.BaseLogEntity;
import com.cms.ets.model.mysql.authority.User;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 操作日志实体类
 * @date 2019年11月15日11:48:05
 * @author Cms
 */
@Document("operate-log")
public class OperateLog extends BaseLogEntity {
    private static final long serialVersionUID = 6439011038306524752L;

    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 操作
     */
    private String action;
    /**
     * 内容  json字符串
     */
    private String content;
    /**
     * 操作结果
     */
    private String result;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void setUser(User user) {
        if (user != null) {
            setUserId(user.getId());
            setUserName(user.getUserName());
            setRealName(user.getRealName());
        }
    }
}
