package com.cms.ets.provider;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.spring4all.mongodb.EnableMongoPlus;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMongoPlus
@EnableDubbo
@MapperScan("com.cms.ets.provider.mapper.*")
public class EtsProvider {
    public static final Log LOG = LogFactory.get(EtsProvider.class);

    public static void main(String[] args) {
        SpringApplication.run(EtsProvider.class, args);
        LOG.info("ets-provider启动成功！！！");
    }
}
