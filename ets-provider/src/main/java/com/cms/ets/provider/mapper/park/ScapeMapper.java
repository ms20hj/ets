package com.cms.ets.provider.mapper.park;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cms.ets.model.mysql.park.Scape;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 景点信息表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
public interface ScapeMapper extends BaseMapper<Scape> {

    List<Scape> pageQuery(IPage page, @Param("name") String name);
}
