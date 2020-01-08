package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.Discount;

/**
 * <p>
 * 优惠配置表 服务类
 * </p>
 *
 * @author cms
 * @since 2020-01-03
 */
public interface IDiscountService extends IService<Discount> {

    public static final String CACHE_DISCOUNT = "discount";
    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return IPage
     */
    IPage<Discount> page(Page<Discount> page, String name);

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 保存或者更新优惠
     * @param discount Discount对象
     * @date 2020年1月8日09:59:14
     */
    void saveOrUpdateEntity(Discount discount);

    /**
     * 根据id查询关联信息
     * @param id 主键
     * @return Discount
     * @date 2020年1月8日15:04:32
     */
    Discount getRelationById(String id);

    /**
     * 查询对象用于编辑
     * 处理关联的对象集合提取id到对应的集合
     * @param id Discount主键
     * @return Discount
     * @date 2020年1月8日15:07:47
     */
    Discount getForEdit(String id);

    /**
     * 根据id删除关联信息
     * @param id 主键
     * @date 2020年1月8日15:40:24
     */
    void removeRelationById(String id);
}
