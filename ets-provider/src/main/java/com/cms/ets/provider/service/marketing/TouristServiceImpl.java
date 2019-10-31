package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.ITouristService;
import com.cms.ets.model.mysql.marketing.Tourist;
import com.cms.ets.provider.mapper.marketing.TouristMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 游客类型表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-31
 */
@Service
public class TouristServiceImpl extends ServiceImpl<TouristMapper, Tourist> implements ITouristService {

    @Override
    public IPage<Tourist> page(Page<Tourist> page, String name) {
        QueryWrapper<Tourist> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("tourist_name", name);
        }
        wrapper.orderByAsc("sort_num");
        return page(page, wrapper);
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<Tourist> wrapper = new QueryWrapper<>();
        wrapper.eq("tourist_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<Tourist> list = list(wrapper);
        return !list.isEmpty();
    }
}
