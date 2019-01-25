package com.tsm.shardingjdbctest.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.tsm.shardingjdbctest.repository")
public class MapperConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public MapperConfig() {
        log.info("Scan mapper dao.");
    }
}
