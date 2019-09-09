package com.cms.ets.mybatis.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 开启mybatiesPlus 分页
 * @date 2019年8月2日09:30:38
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

    /**
     * 开启分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 开启逻辑删除配置
     */
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }
}
