package com.cms.ets.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IMenuService;
import com.cms.ets.model.mysql.authority.Menu;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuInit {

    @Reference
    private IMenuService menuService;

    @Test
    public void init(){
        saveIndexMenu();
    }

    /**
     * 初始化主页菜单
     */
    @Test
    public void saveIndexMenu(){
        Menu menu = new Menu();
        menu.setMenuName("欢迎");
        menu.setIcon("smile");
        menu.setCategory(Menu.CATEGORY_WEB);
        menu.setSort(1);
        menu.setUrl("welcome");
        menuService.save(menu);
    }

    @Test
    public void saveAuthMenu(){
        Menu menu = new Menu();
        menu.setMenuName("用户权限");
        menu.setIcon("team");
        menu.setCategory(Menu.CATEGORY_WEB);
        menu.setSort(2);
        menu.setUrl("authority");
        menuService.save(menu);
    }

    @Test
    public void saveAuthMenuChildren(){
        String parentId = "1215478614922870786";
        Menu menu1 = new Menu();
        menu1.setMenuName("用户管理");
        menu1.setCategory(Menu.CATEGORY_WEB);
        menu1.setSort(21);
        menu1.setUrl("user");
        menu1.setParentId(parentId);

        Menu menu2 = new Menu();
        menu2.setMenuName("角色管理");
        menu2.setCategory(Menu.CATEGORY_WEB);
        menu2.setSort(22);
        menu2.setUrl("role");
        menu2.setParentId(parentId);
        List<Menu> list = Lists.newArrayList(menu1, menu2);
        menuService.saveBatch(list);
    }

    @Test
    public void saveViewMenu(){
        Menu menu = new Menu();
        menu.setMenuName("园区管理");
        menu.setIcon("slack");
        menu.setCategory(Menu.CATEGORY_WEB);
        menu.setSort(3);
        menu.setUrl("park");
        menuService.save(menu);
    }

    @Test
    public void saveViewMenuChildren(){
        String parentId = "1215479641160007681";
        Menu menu1 = new Menu();
        menu1.setMenuName("景区管理");
        menu1.setCategory(Menu.CATEGORY_WEB);
        menu1.setSort(31);
        menu1.setUrl("scenicspot");
        menu1.setParentId(parentId);

        Menu menu2 = new Menu();
        menu2.setMenuName("景点管理");
        menu2.setCategory(Menu.CATEGORY_WEB);
        menu2.setSort(32);
        menu2.setUrl("scape");
        menu2.setParentId(parentId);

        Menu menu3 = new Menu();
        menu3.setMenuName("销售站点管理");
        menu3.setCategory(Menu.CATEGORY_WEB);
        menu3.setSort(33);
        menu3.setUrl("salesite");
        menu3.setParentId(parentId);

        Menu menu4 = new Menu();
        menu4.setMenuName("销售窗口管理");
        menu4.setCategory(Menu.CATEGORY_WEB);
        menu4.setSort(34);
        menu4.setUrl("salewindow");
        menu4.setParentId(parentId);
        List<Menu> list = Lists.newArrayList(menu1, menu2, menu3, menu4);
        menuService.saveBatch(list);
    }

    @Test
    public void saveMarkingMenu(){
        Menu menu = new Menu();
        menu.setMenuName("营销管理");
        menu.setIcon("bank");
        menu.setCategory(Menu.CATEGORY_WEB);
        menu.setSort(4);
        menu.setUrl("marketing");
        menuService.save(menu);
    }

    @Test
    public void saveMarkingMenuChildren(){
        String parentId = "1215480974827044865";
        Menu menu1 = new Menu();
        menu1.setMenuName("旅行社管理");
        menu1.setCategory(Menu.CATEGORY_WEB);
        menu1.setSort(41);
        menu1.setUrl("travelagency");
        menu1.setParentId(parentId);

        Menu menu2 = new Menu();
        menu2.setMenuName("游客管理");
        menu2.setCategory(Menu.CATEGORY_WEB);
        menu2.setSort(42);
        menu2.setUrl("tourist");
        menu2.setParentId(parentId);

        Menu menu3 = new Menu();
        menu3.setMenuName("门票管理");
        menu3.setCategory(Menu.CATEGORY_WEB);
        menu3.setSort(43);
        menu3.setUrl("ticket");
        menu3.setParentId(parentId);

        Menu menu4 = new Menu();
        menu4.setMenuName("优惠管理");
        menu4.setCategory(Menu.CATEGORY_WEB);
        menu4.setSort(44);
        menu4.setUrl("discount");
        menu4.setParentId(parentId);
        List<Menu> list = Lists.newArrayList(menu1, menu2, menu3, menu4);
        menuService.saveBatch(list);
    }
}
