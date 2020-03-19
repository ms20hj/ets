package com.cms.ets.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.authority.IMenuService;
import com.cms.ets.model.mysql.authority.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientMenuInit {
    @Reference
    private IMenuService menuService;

    @Test
    public void saveSaleMenu() {
        Menu menu = new Menu();
        menu.setMenuName("销售");
        menu.setIcon("shop");
        menu.setCategory(Menu.CATEGORY_CLIENT);
        menu.setSort(10);
        menu.setUrl("sale");
        menuService.save(menu);
    }

    @Test
    public void saveSaleChildMenu(){
        String parentId = "1239463535974428673";
        Menu menu1 = new Menu();
        menu1.setMenuName("售票");
        menu1.setCategory(Menu.CATEGORY_CLIENT);
        menu1.setSort(101);
        menu1.setUrl("ticket");
        menu1.setParentId(parentId);
        menuService.save(menu1);
    }
}
