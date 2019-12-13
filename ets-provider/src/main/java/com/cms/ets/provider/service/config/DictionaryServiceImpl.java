package com.cms.ets.provider.service.config;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.config.IDictionaryService;
import com.cms.ets.common.constant.DictionaryConstant;
import com.cms.ets.model.mysql.config.Dictionary;
import com.cms.ets.provider.mapper.config.DictionaryMapper;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-12-12
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Dictionary> getByParentName(String name) {
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_name", name);
        Dictionary parent = getOne(wrapper, false);
        if (parent == null) {
            return Lists.newArrayList();
        }

        QueryWrapper<Dictionary> cqw = new QueryWrapper<>();
        cqw.eq("parent_id", parent.getId());
        return list(cqw);
    }

    @Cacheable(value = CACHE_DICTIONARY, key = "#prefix")
    @Override
    public List<Dictionary> getChildrenByParentPrefix(String prefix) {
        String name = DictionaryConstant.DICTIONARY_CAHCE_PREFIX_KEY.get(prefix);
        if (StringUtils.isEmpty(name)){
            return null;
        }
        return this.getByParentName(name);
    }

    @Override
    public boolean save(Dictionary entity) {
        this.removeCacheByEntity(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Dictionary entity) {
        this.removeCacheByEntity(entity);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        Dictionary entity = this.getById(id);
        this.removeCacheByEntity(entity);
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        idList.stream().forEach(id -> {
            Dictionary entity = this.getById(id);
            this.removeCacheByEntity(entity);
        });
        return super.removeByIds(idList);
    }

    @Override
    public void removeCacheByEntity(Dictionary entity){
        String parentId = entity.getParentId();
        if (StringUtils.isEmpty(parentId)) {
            return;
        }
        Dictionary parentDict = this.getById(parentId);
        if (parentDict == null) {
            return;
        }
        for (String entryKey: DictionaryConstant.DICTIONARY_CAHCE_PREFIX_KEY.keySet()) {
            if (DictionaryConstant.DICTIONARY_CAHCE_PREFIX_KEY.get(entryKey).equals(parentDict.getDictName())) {
                redisTemplate.delete(CACHE_DICTIONARY + entryKey);
                break;
            }
        }
    }
}
