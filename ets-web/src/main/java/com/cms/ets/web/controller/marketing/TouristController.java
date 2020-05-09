package com.cms.ets.web.controller.marketing;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.marketing.ITouristService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.marketing.Tourist;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 游客类型表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-10-31
 */
@RestController
@RequestMapping("tourist")
public class TouristController extends BaseController {

    @Reference
    private ITouristService touristService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<Tourist> page, String name) {
        IPage<Tourist> iPage = touristService.page(page, name);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = Tourist.MODULE_NAME, menuName = Tourist.MENU_NAME)
    public HandleResult save(@RequestBody Tourist tourist) {
        touristService.save(tourist);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = Tourist.MODULE_NAME, menuName = Tourist.MENU_NAME)
    public HandleResult update(@RequestBody Tourist tourist) {
        touristService.updateById(tourist);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = touristService.checkNameExist(name, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = Tourist.MODULE_NAME, menuName = Tourist.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = touristService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据ID查询")
    public HandleResult getById(String id){
        Tourist tourist = touristService.getById(id);
        return success(tourist);
    }

    @GetMapping("list")
    @ApiOperation("不分页列表查询")
    public HandleResult list(){
        List<Tourist> list = touristService.list();
        return success(list);
    }
}
