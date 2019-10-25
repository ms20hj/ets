package com.cms.ets.web.controller.park;


import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cms.ets.api.park.ISaleSiteService;
import com.cms.ets.common.response.HandleResult;
import com.cms.ets.model.mysql.park.SaleSite;
import com.cms.ets.web.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 销售站点信息表 前端控制器
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
@RestController
@RequestMapping("saleSite")
public class SaleSiteController extends BaseController {

    @Reference
    private ISaleSiteService saleSiteService;

    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return HandleResult
     * @author ChenMingsen
     */
    @GetMapping("page")
    @ApiOperation("分页查询")
    public HandleResult page(Page<SaleSite> page, String name) {
        IPage<SaleSite> iPage = saleSiteService.page(page, name);
        return success(iPage);
    }

    @PostMapping("save")
    @ApiOperation("保存")
    public HandleResult save(@RequestBody SaleSite saleSite) {
        saleSiteService.save(saleSite);
        return success();
    }

    @PostMapping("update")
    @ApiOperation("更新")
    public HandleResult update(@RequestBody SaleSite saleSite) {
        saleSiteService.updateById(saleSite);
        return success();
    }

    @GetMapping("checkNameExist")
    @ApiOperation("检验用户名是否存在")
    public HandleResult checkNameExist(String name, String id){
        boolean flag = saleSiteService.checkNameExist(name, id);
        return success(flag);
    }

    @DeleteMapping("remove")
    @ApiOperation("删除")
    public HandleResult remove(@RequestBody List<String> ids){
        boolean flag = saleSiteService.removeByIds(ids);
        return verifyResp(flag);
    }

    @GetMapping("getById")
    @ApiOperation("根据id查询")
    public HandleResult getById(String id) {
        SaleSite saleSite = saleSiteService.getById(id);
        return success(saleSite);
    }
}
