package com.cms.ets.provider.service.park;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.park.ISaleSiteService;
import com.cms.ets.model.mysql.park.SaleSite;
import com.cms.ets.provider.mapper.park.SaleSiteMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 销售站点信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
@Service
public class SaleSiteServiceImpl extends ServiceImpl<SaleSiteMapper, SaleSite> implements ISaleSiteService {

    @Override
    public IPage<SaleSite> page(Page<SaleSite> page, String name) {
        List<SaleSite> list = baseMapper.pageQuery(page, name);
        page.setRecords(list);
        return page;
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<SaleSite> wrapper = new QueryWrapper<>();
        wrapper.eq("site_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<SaleSite> list = list(wrapper);
        return !list.isEmpty();
    }
}
