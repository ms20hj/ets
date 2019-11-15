package com.cms.ets.model.mysql.park;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cms.ets.model.mysql.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 销售窗口信息表
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
@TableName("t_sale_window")
public class SaleWindow extends BaseEntity {

    private static final long serialVersionUID = 1L;
    public static final String MODULE_NAME = "园区管理";

    public static final String MENU_NAME = "销售窗口管理";
    /**
     * 窗口名称
     */
    @TableField("window_name")
    private String windowName;

    /**
     * 所属销售站点id
     */
    @TableField("sale_site_id")
    private String saleSiteId;

    /**
     * 销售窗口mac地址
     */
    @TableField("mac")
    private String mac;

    /**
     * 销售站点
     */
    @TableField(exist = false)
    private SaleSite saleSite;

    public String getWindowName() {
        return windowName;
    }

    public void setWindowName(String windowName) {
        this.windowName = windowName;
    }
    public String getSaleSiteId() {
        return saleSiteId;
    }

    public void setSaleSiteId(String saleSiteId) {
        this.saleSiteId = saleSiteId;
    }
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public SaleSite getSaleSite() {
        return saleSite;
    }

    public void setSaleSite(SaleSite saleSite) {
        this.saleSite = saleSite;
    }

    @Override
    public String toString() {
        return "SaleWindow{" +
            "windowName=" + windowName +
            ", saleSiteId=" + saleSiteId +
            ", mac=" + mac +
        "}";
    }
}
