package com.cms.ets.api.marketing;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.marketing.TravelAgency;

import java.util.List;

/**
 * <p>
 * 旅行社信息表 服务类
 * </p>
 *
 * @author cms
 * @since 2019-10-29
 */
public interface ITravelAgencyService extends IService<TravelAgency> {
    /**
     * 分页查询
     * @param page 分页参数
     * @param name 名称
     * @return IPage
     */
    IPage<TravelAgency> page(Page<TravelAgency> page, String name, String parentId);

    /**
     * 校验名称是否已存在
     * @param name 名称
     * @param id 主键
     * @return 存在true, 不存在false
     */
    boolean checkNameExist(String name, String id);

    /**
     * 根据父级ID查询
     * @param parentId 父级ID
     * @return List<TravelAgency>
     * @date 2019年10月29日14:05:08
     */
    List<TravelAgency> getByParentId(String parentId);

    /**
     * 获取旅行社类别树形数据
     * @return List<TravelAgency>
     * @date 2019年10月29日14:58:15
     */
    List<TravelAgency> getTreeTravel();

    /**
     * 查询根节点
     * 只查询id，name 这两个字段
     * @return List<TravelAgency>
     * @date 2020年1月7日17:15:36
     */
    List<TravelAgency> getSimpleLeafList();
    /**
     * 获取旅行社类别树形数据,不包含根节点
     * 非全表字段查询，仅查询id,name,parentId
     * @return List<TravelAgency>
     * @date 2019年10月29日14:58:15
     */
    List<TravelAgency> getTreeDataExceptRoot();

    /**
     * 根据父级ID查询
     * 非全表字段查询，仅查询id,name,parentId
     * @param parentId 父id
     * @return List<TravelAgency>
     * @date 2020年3月19日14:10:32
     */
    List<TravelAgency> getSimpleByParentId(String parentId);
}
