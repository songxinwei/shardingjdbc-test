package com.tsm.shardingjdbctest.config;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.tsm.shardingjdbctest.util.StringUtils;
import com.zaxxer.hikari.HikariConfig;
import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.transaction.api.TransactionType;
import io.shardingsphere.transaction.api.TransactionTypeHolder;
import io.shardingsphere.transaction.spi.xa.XATransactionManager;
import io.shardingsphere.transaction.xa.manager.AtomikosTransactionManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: walkingWind
 * @Date: 2019/1/22 16:07
 * @Version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(ShardingEnvironment.class)
@MapperScan(basePackages = {"com.tsm.shardingjdbctest.repository"},sqlSessionFactoryRef = "mybatisSqlSessionFactory")
public class ShardingDataSourceConfig {
    private static final Logger log = LoggerFactory.getLogger(ShardingDataSourceConfig.class);

    @Autowired(required = false)
    private ShardingEnvironment shardingEnvironment;

    @Autowired
    private Environment env;

    /**
     * 配置数据源
     * @return
     * @throws Exception
     */
    @Bean("mybatisDataSource")
    @Primary
    public DataSource ShardingDataSource() throws Exception {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        shardingEnvironment.getDataSources().forEach((k, v) -> dataSourceMap.put(k,configDataSource(v)));
        DataSource dataSource =ShardingDataSourceFactory.createDataSource(dataSourceMap,
                shardingEnvironment.initShardingRule(), new HashMap<>(),new Properties());
        log.info("ShardingDataSource config complete！！");
        return dataSource;
    }

    /**
     * 可添加数据源一些配置信息
     * @param hikariConfig
     */
    private DataSource configDataSource(HikariConfig hikariConfig) {
        hikariConfig.setMaximumPoolSize(60);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setConnectionTimeout(15000);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        XATransactionManager xaTransactionManager=new AtomikosTransactionManager();
//        xaTransactionManager.wrapDataSource();
        return dataSource;
    }

    @Bean(name = "mybatisTransactionManager")
    public DataSourceTransactionManager mybatisTransactionManager(@Qualifier("mybatisDataSource") DataSource dataSource) throws SQLException {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mybatisSqlSessionFactory")
    public SqlSessionFactory mybatisSqlSessionFactory(@Qualifier("mybatisDataSource") DataSource mybatisDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mybatisDataSource);
        sessionFactory.setTypeAliasesPackage(StringUtils.dealNull(env.getProperty("mybatis.type-aliases-package")));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(StringUtils.dealNull(env.getProperty("mybatis.mapper-locations"))));
        return sessionFactory.getObject();
    }

}
