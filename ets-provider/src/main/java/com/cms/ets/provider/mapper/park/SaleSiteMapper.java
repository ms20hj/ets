package com.cms.ets.provider.mapper.park;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cms.ets.model.mysql.park.SaleSite;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 销售站点信息表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
public interface SaleSiteMapper extends BaseMapper<SaleSite> {

    List<SaleSite> pageQuery(IPage page, @Param("name") String name);
}
