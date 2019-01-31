package com.tsm.shardingjdbctest.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tsm.shardingjdbctest.util.StringUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.shardingsphere.api.algorithm.masterslave.MasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.algorithm.masterslave.RandomMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.algorithm.masterslave.RoundRobinMasterSlaveLoadBalanceAlgorithm;
import io.shardingsphere.api.config.rule.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: walkingWind
 * @Date: 2019/1/22 16:03
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingEnvironment {
    private Map<String,HikariConfig> dataSources = new HashMap<>();

    private Map<String,Object> shardingRule;

    private Map<String,Object> masterSlaveRule;


    public ShardingRuleConfiguration initShardingRule() throws Exception{
        try {
            List<TableRuleConfiguration> tableRuleConfigurationList=new ArrayList<TableRuleConfiguration>();
            Map<String,JSONObject> tables=(Map<String,JSONObject>)shardingRule.get("tables");
            Iterator<String> iterator=tables.keySet().iterator();
            while (iterator.hasNext()){
                String key=iterator.next();
                JSONObject eachSetting=JSONObject.parseObject(JSON.toJSONString(tables.get(key)));
                TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
                orderTableRuleConfig.setLogicTable(key);
                orderTableRuleConfig.setActualDataNodes(eachSetting.getString("actual-data-nodes"));
                JSONObject databaseStrategy=eachSetting.getJSONObject("database-strategy");
                JSONObject tableStrategy=eachSetting.getJSONObject("table-strategy");
                JSONObject databaseStrategyInline=databaseStrategy.getJSONObject("inline");
                JSONObject tableStrategyInline=tableStrategy.getJSONObject("inline");
                orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(databaseStrategyInline.getString("sharding-column"),databaseStrategyInline.getString("algorithm-inline-expression")));
                orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration(tableStrategyInline.getString("sharding-column"), tableStrategyInline.getString("algorithm-inline-expression")));
                tableRuleConfigurationList.add(orderTableRuleConfig);
            }

            ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
            shardingRuleConfig.getTableRuleConfigs().addAll(tableRuleConfigurationList);
            shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());
            return shardingRuleConfig;
        }catch (Exception e){
            throw new RuntimeException("shardingRule init error!");
        }
    }


    List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        List<MasterSlaveRuleConfiguration> list=new ArrayList<MasterSlaveRuleConfiguration>();
        masterSlaveRule.forEach((k,v)->{
            Map map=(Map)v;
            MasterSlaveRuleConfiguration masterSlaveRuleConfig = new MasterSlaveRuleConfiguration(k,StringUtils.dealNull(map.get("master-data-source-name")),((Map)map.get("slave-data-source-names")).values(),StringUtils.dealNull(map.get("load-balance-algorithm-type")).equals("round_robin")?new RoundRobinMasterSlaveLoadBalanceAlgorithm():new RandomMasterSlaveLoadBalanceAlgorithm());
            list.add(masterSlaveRuleConfig);
        });
        return list;
    }

}
