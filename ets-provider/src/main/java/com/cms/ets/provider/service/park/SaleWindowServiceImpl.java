package com.cms.ets.provider.service.park;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.park.ISaleWindowService;
import com.cms.ets.model.mysql.park.SaleWindow;
import com.cms.ets.provider.mapper.park.SaleWindowMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 销售窗口信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
@Service
public class SaleWindowServiceImpl extends ServiceImpl<SaleWindowMapper, SaleWindow> implements ISaleWindowService {

    @Override
    public IPage<SaleWindow> page(Page<SaleWindow> page, String name) {
        List<SaleWindow> list = baseMapper.pageQuery(page, name);
        page.setRecords(list);
        return page;
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<SaleWindow> wrapper = new QueryWrapper<>();
        wrapper.eq("window_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<SaleWindow> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public SaleWindow getByMac(String mac) {
        QueryWrapper<SaleWindow> wrapper = new QueryWrapper<>();
        wrapper.eq("mac", mac);
        return getOne(wrapper, false);
    }

    @Override
    public boolean checkMacExist(String mac, String id) {
        QueryWrapper<SaleWindow> wrapper = new QueryWrapper<>();
        wrapper.eq("mac", mac);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<SaleWindow> list = list(wrapper);
        return !list.isEmpty();
    }
}
