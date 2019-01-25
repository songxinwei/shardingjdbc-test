package com.tsm.shardingjdbctest.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: 江千月
 * @Date: 2019/1/22 16:03
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingEnvironment {
    private Map<String,HikariConfig> dataSources = new HashMap<>();

    private Map<String,Object> shardingRule;

    /**
     * 初始化ShardingRuleConfiguration
     * @return
     * @throws Exception
     */
    public ShardingRuleConfiguration initShardingRule() throws Exception{
        try {
            List<TableRuleConfiguration> tableRuleConfigurationList=new ArrayList<TableRuleConfiguration>();
            Map<String,JSONObject> tables=(Map<String,JSONObject>)shardingRule.get("tables");
            Iterator<String> iterator=tables.keySet().iterator();
            while (iterator.hasNext()){
                String key=iterator.next();
                JSONObject eachSetting=JSONObject.parseObject(JSON.toJSONString(tables.get(key)));
                // 配置Order表规则
                TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
                orderTableRuleConfig.setLogicTable(key);
                orderTableRuleConfig.setActualDataNodes(eachSetting.getString("actual-data-nodes"));
                // 配置分库 + 分表策略
                JSONObject databaseStrategy=eachSetting.getJSONObject("database-strategy");
                JSONObject tableStrategy=eachSetting.getJSONObject("table-strategy");
                JSONObject databaseStrategyInline=databaseStrategy.getJSONObject("inline");
                JSONObject tableStrategyInline=tableStrategy.getJSONObject("inline");
                orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(databaseStrategyInline.getString("sharding-column"),databaseStrategyInline.getString("algorithm-inline-expression")));
                orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration(tableStrategyInline.getString("sharding-column"), tableStrategyInline.getString("algorithm-inline-expression")));
                tableRuleConfigurationList.add(orderTableRuleConfig);
            }

            // 配置分片规则
            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.getTableRuleConfigs().addAll(tableRuleConfigurationList);
            return shardingRuleConfig;
        }catch (Exception e){
            throw new RuntimeException("shardingRule init error!");
        }
    }
}
