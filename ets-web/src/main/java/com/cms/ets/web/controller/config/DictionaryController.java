package com.cms.ets.web.controller.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cms.ets.api.config.IDictionaryService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.config.Dictionary;
import com.cms.ets.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典controller 控制器
 * @date 2019年12月12日14:47:43
 * @author Cms
 */
@RestController
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {

    @Reference
    private IDictionaryService dictionaryService;

    @GetMapping("getChildrenByPrefix")
    public HandleResult getChildrenByPrefix(String prefix){
        List<Dictionary> list = dictionaryService.getChildrenByParentPrefix(prefix);
        return success(list);
    }
}
