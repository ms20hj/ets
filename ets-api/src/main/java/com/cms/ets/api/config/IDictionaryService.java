package com.cms.ets.api.config;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cms.ets.model.mysql.config.Dictionary;

import java.util.List;

/**
 * <p>
 * 字典 服务类
 * </p>
 *
 * @author cms
 * @since 2019-12-12
 */
public interface IDictionaryService extends IService<Dictionary> {

    public static final String CACHE_DICTIONARY = "dictionary";

    /**
     * 根据名称查询
     * @param name 父级名称
     * @return List<Dictionary>
     */
    List<Dictionary> getByParentName(String name);

    /**
     * 根据父级名称对照的代名进行查询下一级
     * @param prefix 父级的字段名词对应值，比如：门票介质-》physical。
     *               对应参照值在DictionaryConstant这个类里面
     * @return List<Dictionary>
     */
    List<Dictionary> getChildrenByParentPrefix(String prefix);

    /**
     * 根据对象删除缓存
     * 实现的是根据entity获取它的parentId对应的对象，再去匹配对应的字典key
     * @param entity 对象实体类
     */
    void removeCacheByEntity(Dictionary entity);
}
