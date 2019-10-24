package com.cms.ets.provider.service.park;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cms.ets.api.park.IScapeService;
import com.cms.ets.model.mysql.park.Scape;
import com.cms.ets.provider.mapper.park.ScapeMapper;

/**
 * <p>
 * 景点信息表 服务实现类
 * </p>
 *
 * @author cms
 * @since 2019-10-24
 */
@Service
public class ScapeServiceImpl extends ServiceImpl<ScapeMapper, Scape> implements IScapeService {

}
