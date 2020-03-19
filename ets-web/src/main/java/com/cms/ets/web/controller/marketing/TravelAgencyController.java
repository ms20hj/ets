package com.cms.ets.web.controller.marketing;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.marketing.ITravelAgencyService;
import com.cms.ets.common.constant.OperateLogConstant;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.marketing.TravelAgency;
import com.cms.ets.web.annotation.OperationLog;
import com.cms.ets.web.controller.BaseController;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 旅行社信息表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-10-29
 */
@RestController
@RequestMapping("travelAgency")
public class TravelAgencyController extends BaseController {

    @Reference
    private ITravelAgencyService travelAgencyService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<TravelAgency> page, String name, String parentId) {
        IPage<TravelAgency> iPage = travelAgencyService.page(page, name, parentId);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    @OperationLog(action = OperateLogConstant.ADD, moduleName = TravelAgency.MODULE_NAME, menuName = TravelAgency.MENU_NAME)
    public HandleResult save(@RequestBody TravelAgency travelAgency) {
        travelAgencyService.save(travelAgency);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    @OperationLog(action = OperateLogConstant.EDIT, moduleName = TravelAgency.MODULE_NAME, menuName = TravelAgency.MENU_NAME)
    public HandleResult update(@RequestBody TravelAgency travelAgency) {
        travelAgencyService.updateById(travelAgency);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = travelAgencyService.checkNameExist(name, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    @OperationLog(action = OperateLogConstant.REMOVE, moduleName = TravelAgency.MODULE_NAME, menuName = TravelAgency.MENU_NAME)
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = travelAgencyService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public HandleResult getById(String id) {
        TravelAgency travelAgency = travelAgencyService.getById(id);
        return success(travelAgency);
    }

    @GetMapping("getTreeTravel")
    @ApiOperation("获取旅行社类别树形数据")
    public HandleResult getTreeTravel(){
        List<TravelAgency> list = travelAgencyService.getTreeTravel();
        return success(list);
    }

    @GetMapping("getTreeDataExceptRoot")
    @ApiOperation("获取旅行社类别树形数据，不包含根节点")
    public HandleResult getTreeDataExceptRoot() {
        List<TravelAgency> list = travelAgencyService.getTreeDataExceptRoot();
        return success(list);
    }


}
