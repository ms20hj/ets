package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.marketing.ITicketCategoryService;
import com.cms.ets.model.mysql.marketing.TicketCategory;
import com.cms.ets.provider.mapper.marketing.TicketCategoryMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * <p>
 * 门票分类表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@Service
public class TicketCategoryServiceImpl extends ServiceImpl<TicketCategoryMapper, TicketCategory> implements ITicketCategoryService {

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<TicketCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("category_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<TicketCategory> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public List<TicketCategory> listTree() {
        List<TicketCategory> children = list();
        TicketCategory ticketCategory = new TicketCategory();
        ticketCategory.setId(TicketCategory.ROOT_ID);
        ticketCategory.setCategoryName("门票种类");
        ticketCategory.setChildren(children);
        return Lists.newArrayList(ticketCategory);
    }
}
