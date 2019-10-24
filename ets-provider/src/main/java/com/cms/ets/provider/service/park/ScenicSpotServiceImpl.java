package com.cms.ets.provider.service.park;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.park.IScenicSpotService;
import com.cms.ets.model.mysql.park.ScenicSpot;
import com.cms.ets.provider.mapper.park.ScenicSpotMapper;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 景区信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
@Service
public class ScenicSpotServiceImpl extends ServiceImpl<ScenicSpotMapper, ScenicSpot> implements IScenicSpotService {

    @Override
    public IPage<ScenicSpot> page(Page<ScenicSpot> page, String name) {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("spot_name", name);
        }
        return page(page, wrapper);
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<ScenicSpot> wrapper = new QueryWrapper<>();
        wrapper.eq("spot_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", name);
        }
        List<ScenicSpot> list = list(wrapper);
        return !list.isEmpty();
    }
}
