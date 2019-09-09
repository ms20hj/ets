package com.cms.ets.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatis 自动填充字段信息
 * @date 2019年7月22日15:36:02
 * @author cms
 */
@Component
public class AutoSetMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATE_ID = "createId";

    private static final String UPDATE_ID = "updateId";

    private static final String CREATE_TIME = "createTime";

    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName(CREATE_TIME, new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }
}
