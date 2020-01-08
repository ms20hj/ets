package com.cms.ets.provider.mapper.marketing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cms.ets.model.mysql.marketing.Discount;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 优惠配置表 Mapper 接口
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
public interface DiscountMapper extends BaseMapper<Discount> {

    /**
     * 查询关联信息
     * @param id 主键
     * @return Discount
     * @date 2020年1月8日15:01:58
     */
    Discount getRelation(@Param("id") String id);
}
