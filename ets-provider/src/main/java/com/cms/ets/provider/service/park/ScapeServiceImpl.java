package com.cms.ets.provider.service.park;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.park.IScapeService;
import com.cms.ets.model.mysql.park.Scape;
import com.cms.ets.provider.mapper.park.ScapeMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 景点信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
@Service
public class ScapeServiceImpl extends ServiceImpl<ScapeMapper, Scape> implements IScapeService {

    @Override
    public IPage<Scape> page(Page<Scape> page, String name) {
        List<Scape> list = baseMapper.pageQuery(page, name);
        return page.setRecords(list);
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<Scape> wrapper = new QueryWrapper<>();
        wrapper.eq("scape_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<Scape> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public List<Scape> getByScenicSpotId(String scenicSpotId) {
        QueryWrapper<Scape> wrapper = new QueryWrapper<>();
        wrapper.eq("scenic_spot_id", scenicSpotId);
        return list(wrapper);
    }
}
