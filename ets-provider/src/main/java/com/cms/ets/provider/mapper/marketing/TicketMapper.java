package com.cms.ets.provider.mapper.marketing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cms.ets.model.mysql.marketing.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门票信息表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2019-12-09
 */
public interface TicketMapper extends BaseMapper<Ticket> {

    List<Ticket> pageQuery(IPage<Ticket> page, @Param("name") String name, @Param("categoryId") String categoryId);

    Ticket getById(@Param("id") String id);
}
