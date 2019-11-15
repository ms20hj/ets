package com.cms.ets.api.log;

import com.cms.ets.model.mongo.log.OperateLog;

import java.util.List;

/**
 * 操作日志接口服务类
 * @date 2019年11月15日14:12:26
 * @author Cms
 */
public interface IOperateLogService {

    /**
     * 保存
     * @param operateLog 操作日志
     */
    void save(OperateLog operateLog);

    /**
     * 批量保存
     * @param list 日志集合
     */
    void batchSave(List<OperateLog> list);
}
