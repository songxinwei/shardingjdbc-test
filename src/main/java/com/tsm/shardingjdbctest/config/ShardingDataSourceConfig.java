package com.tsm.shardingjdbctest.config;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: 江千月
 * @Date: 2019/1/22 16:07
 * @Version 1.0
 * 配置数据源详细信息
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(ShardingEnvironment.class)
@ConditionalOnProperty({"sharding.jdbc.data-sources.ds_master.jdbc-url",
        "sharding.jdbc.master-slave-rule.master-data-source-name" })
public class ShardingDataSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(ShardingDataSourceConfig.class);

    @Autowired(required = false)
    private ShardingEnvironment shardingEnvironment;

    /**
     * 配置数据源
     *
     * @return
     * @throws Exception
     */
    @Bean("shardingDataSource")
    public DataSource ShardingDataSource() throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        shardingEnvironment.getDataSources().forEach((k, v) -> dataSourceMap.put(k,configDataSource(v)));
        //MasterSlaveDataSourceFactory用于创建独立使用读写分离的JDBC驱动（见读写分离demo）
        //ShardingDataSourceFactory用于创建分库分表或分库分表+读写分离的JDBC驱动
        DataSource dataSource =ShardingDataSourceFactory.createDataSource(dataSourceMap,
                shardingEnvironment.initShardingRule(), new HashMap<>(),new Properties());
                log.info("ShardingDataSource config complete！！");
        return dataSource;
    }

    /**
     * 可添加数据源一些配置信息
     *
     * @param hikariConfig
     */
    private DataSource configDataSource(HikariConfig hikariConfig) {
        hikariConfig.setMaximumPoolSize(60);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setConnectionTimeout(15000);//连接超时（毫秒）
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }

}
