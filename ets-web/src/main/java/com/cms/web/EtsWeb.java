package com.cms.web;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class EtsWeb {

    private static final Log LOG = LogFactory.get(EtsWeb.class);

    public static void main(String[] args) {
        SpringApplication.run(EtsWeb.class, args);
        LOG.info("ets-web 启动成功！！！");
    }
}
