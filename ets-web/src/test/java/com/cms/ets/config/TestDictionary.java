package com.cms.ets.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.config.IDictionaryService;
import com.cms.ets.model.mysql.config.Dictionary;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDictionary {

    @Reference
    private IDictionaryService dictionaryService;

    @Test
    public void saveDict(){
        Dictionary dict = new Dictionary();
        dict.setDictName("打印模板");
        dict.setDictValue("打印模板");
        dictionaryService.save(dict);
    }

    @Test
    public void saveChildrenPhysical() {
        String parentId = "1204954814653358081";
        Dictionary d1 = new Dictionary();
        d1.setParentId(parentId);
        d1.setDictName("打印条码票");
        d1.setDictValue("0");

        Dictionary d2 = new Dictionary();
        d2.setParentId(parentId);
        d2.setDictName("ic卡票");
        d2.setDictValue("1");

        Dictionary d3 = new Dictionary();
        d3.setParentId(parentId);
        d3.setDictName("身份证票");
        d3.setDictValue("2");

        List<Dictionary> list = Lists.newArrayList(d1, d2, d3);
        dictionaryService.saveBatch(list);
    }

    @Test
    public void saveChildrenDeadlineUnit(){
        String parentId = "1204966859889139713";
        Dictionary d1 = new Dictionary();
        d1.setParentId(parentId);
        d1.setDictName("天");
        d1.setDictValue("D");

        Dictionary d2 = new Dictionary();
        d2.setParentId(parentId);
        d2.setDictName("月");
        d2.setDictValue("M");

        Dictionary d3 = new Dictionary();
        d3.setParentId(parentId);
        d3.setDictName("年");
        d3.setDictValue("Y");

        List<Dictionary> list = Lists.newArrayList(d1, d2, d3);
        dictionaryService.saveBatch(list);
    }

    @Test
    public void saveChildrenPrintMethod(){
        String parentId = "1204967724511354882";
        Dictionary d1 = new Dictionary();
        d1.setParentId(parentId);
        d1.setDictName("一人一票");
        d1.setDictValue("0");

        Dictionary d2 = new Dictionary();
        d2.setParentId(parentId);
        d2.setDictName("一票多人");
        d2.setDictValue("1");

        List<Dictionary> list = Lists.newArrayList(d1, d2);
        dictionaryService.saveBatch(list);
    }

    @Test
    public void saveChildrenPrintTemplate(){
        String parentId = "1204968803965820929";
        Dictionary d1 = new Dictionary();
        d1.setParentId(parentId);
        d1.setDictName("模板1");
        d1.setDictValue("print_template1");

        Dictionary d2 = new Dictionary();
        d2.setParentId(parentId);
        d2.setDictName("模板2");
        d2.setDictValue("print_template12");

        List<Dictionary> list = Lists.newArrayList(d1, d2);
        dictionaryService.saveBatch(list);
    }

    @Test
    public void saveDiscountWay(){
//        Dictionary dw = new Dictionary();
//        dw.setDictName("优惠方式");
//        dw.setDictValue("优惠方式");
//        dictionaryService.save(dw);

        String parentId = "1214000423779627010";
        Dictionary dw1 = new Dictionary();
        dw1.setDictName("打折");
        dw1.setDictValue("0");
        dw1.setParentId("1214000423779627010");

        Dictionary dw2 = new Dictionary();
        dw2.setDictName("减免");
        dw2.setDictValue("1");
        dw2.setParentId("1214000423779627010");

        Dictionary dw3 = new Dictionary();
        dw3.setDictName("折后价");
        dw3.setDictValue("2");
        dw3.setParentId("1214000423779627010");

        List<Dictionary> list = Lists.newArrayList(dw1, dw2, dw3);
        dictionaryService.saveBatch(list);
    }
}
