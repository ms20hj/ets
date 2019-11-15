package com.cms.ets.mongo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.cms.ets.api.log.IOperateLogService;
import com.cms.ets.model.mongo.log.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

/**
 * 操作日志service实现类
 * @date 2019年11月15日14:13:02
 * @author Cms
 */
@Service
public class OperateLogServiceImpl implements IOperateLogService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(OperateLog operateLog) {
        mongoTemplate.insert(operateLog);
    }

    @Override
    public void batchSave(List<OperateLog> list) {
        mongoTemplate.insert(list, OperateLog.class);
    }
}
