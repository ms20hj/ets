package com.cms.ets.model.conversion;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单路由对象
 * @date 2020年1月10日15:22:35
 * @author Cms
 */
public class Route implements Serializable {
    private static final long serialVersionUID = -4380502224443751907L;

    private String id;

    private String name;

    private String path;

    private String icon;

    private List<Route> children;

    public Route() {
    }

    public Route(String id, String name, String path, String icon) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public Route(String id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Route> getChildren() {
        return children;
    }

    public void setChildren(List<Route> children) {
        this.children = children;
    }
}
