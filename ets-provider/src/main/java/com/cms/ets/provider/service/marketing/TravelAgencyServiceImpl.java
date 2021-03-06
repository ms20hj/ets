package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.ITravelAgencyService;
import com.cms.ets.model.mysql.marketing.TravelAgency;
import com.cms.ets.provider.mapper.marketing.TravelAgencyMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 旅行社信息表 服务实现类
 * </p>
 * @author cms
 * @since 2019-10-29
 */
@Service
public class TravelAgencyServiceImpl extends ServiceImpl<TravelAgencyMapper, TravelAgency> implements ITravelAgencyService {

    @Override
    public IPage<TravelAgency> page(Page<TravelAgency> page, String name, String parentId) {
        QueryWrapper<TravelAgency> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
        wrapper.orderByAsc("sort_num");
        return page(page, wrapper);
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<TravelAgency> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<TravelAgency> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public List<TravelAgency> getByParentId(String parentId) {
        QueryWrapper<TravelAgency> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.orderByAsc("sort_num");
        return list(wrapper);
    }

    @Override
    public List<TravelAgency> getTreeTravel() {
        TravelAgency root = getById(TravelAgency.ROOT_ID);
        root.setChildren(getByParentId(TravelAgency.ROOT_ID));
        return Lists.newArrayList(root);
    }

    @Override
    public List<TravelAgency> getSimpleLeafList() {
        QueryWrapper<TravelAgency> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "parent_id");
        wrapper.ne("parent_id", TravelAgency.ROOT_ID);
        wrapper.orderByAsc("sort_num");
        return list(wrapper);
    }

    @Override
    public List<TravelAgency> getTreeDataExceptRoot() {
        QueryWrapper<TravelAgency> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "parent_id");
        wrapper.eq("parent_id", TravelAgency.ROOT_ID);
        List<TravelAgency> list = list(wrapper);
        list.stream().forEach(ta -> ta.setChildren(getSimpleByParentId(ta.getParentId())));
        return list;
    }

    @Override
    public List<TravelAgency> getSimpleByParentId(String parentId) {
        QueryWrapper<TravelAgency> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "parent_id");
        wrapper.eq("parent_id", parentId);
        List<TravelAgency> list = list(wrapper);
        return list;
    }
}
