package com.cms.ets;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableDubbo
//@EnableSwagger2
public class EtsWeb extends SpringBootServletInitializer {

    private static final Log LOG = LogFactory.get(EtsWeb.class);

    public static void main(String[] args) {
        SpringApplication.run(EtsWeb.class, args);
        LOG.info("ets-web 启动成功！！！");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EtsWeb.class);
    }
}
