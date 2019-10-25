package com.cms.ets.provider.mapper.park;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cms.ets.model.mysql.park.SaleWindow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 销售窗口信息表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2019-10-25
 */
public interface SaleWindowMapper extends BaseMapper<SaleWindow> {

    List<SaleWindow> pageQuery(IPage page, @Param("name") String name);
}
