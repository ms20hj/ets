package com.cms.ets.provider.service.marketing;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.config.IDictionaryService;
import com.cms.ets.api.marketing.ITicketService;
import com.cms.ets.api.park.IScenicSpotService;
import com.cms.ets.model.mysql.config.Dictionary;
import com.cms.ets.model.mysql.marketing.Ticket;
import com.cms.ets.model.mysql.park.ScenicSpot;
import com.cms.ets.provider.mapper.marketing.TicketMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门票信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements ITicketService {

    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private IScenicSpotService scenicSpotService;

    @Override
    public IPage<Ticket> page(Page<Ticket> page, String name, String categoryId) {
        List<Ticket> list = baseMapper.pageQuery(page, name, categoryId);
        page.setRecords(list);
        return page;
    }

    @Override
    public boolean checkNameExist(String name, String id) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_name", name);
        if (StringUtils.isNotEmpty(id)) {
            wrapper.ne("id", id);
        }
        List<Ticket> list = list(wrapper);
        return !list.isEmpty();
    }

    @Override
    public List<Ticket> getByCategoryId(String categoryId) {
        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("ticket_gategory_id", categoryId);
        return list(wrapper);
    }

    @Override
    public Map<String, Object> getTicketSelectParams() {
        List<ScenicSpot> scenicSpotList = scenicSpotService.getSimpleList();
        List<Dictionary> physicalList = dictionaryService.getChildrenByParentPrefix(Ticket.TICKET_PHYSICAL);
        List<Dictionary> deadlineUnitList = dictionaryService.getChildrenByParentPrefix(Ticket.TICKET_DEADLINEUNIT);
        List<Dictionary> printMethodList = dictionaryService.getChildrenByParentPrefix(Ticket.TICKET_PRINTMETHOD);
        List<Dictionary> printTemplateList = dictionaryService.getChildrenByParentPrefix(Ticket.TICKET_PRINTTEMPLATE);
        return ImmutableMap.of("scenicSpotList", scenicSpotList, "physicalList", physicalList, "deadlineUnitList", deadlineUnitList,
                "printMethodList", printMethodList, "printTemplateList", printTemplateList);
    }
}
