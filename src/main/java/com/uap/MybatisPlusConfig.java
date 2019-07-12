package com.uap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;

@Configuration
public class MybatisPlusConfig {
    /**
    * 乐观锁 插件
    * @return
    */
    @Bean
    public OptimisticLockerInterceptor optimisticLoker() {
        return new OptimisticLockerInterceptor();
    }
}
