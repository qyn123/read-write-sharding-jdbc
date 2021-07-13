package com.qiaoyn.level.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 基于java配置sharding-jdbc，这样可以不用在xml文件中配置
 * @author yn.qiao
 * @version 1.0
 * @create 2021-07-08 16:32
 **/
@Configuration
public class ShardingConfig {

    /**
     * 配置数据源
     */
     Map<String, DataSource> createDataSourceMap(){
         DruidDataSource dataSource = new DruidDataSource();
         dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
         dataSource.setUrl("jdbc:mysql://localhost:3306/order_db?useUnicode=true");
         dataSource.setUsername("root");
         dataSource.setPassword("root");
         Map<String, DataSource> result = new HashMap<>(16);
         result.put("m1", dataSource);
         return result;
    }


    /**
     * 定义主键生成策略
     */
    private static KeyGeneratorConfiguration getKeyGeneratorConfiguration() {
        return new KeyGeneratorConfiguration("SNOWFLAKE","order_id");
    }


    /**
     * 定义t_order表的分片策略
     */
    TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("t_order","m1.t_order_$->{1..2}");
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_$->{order_id % 2 + 1}"));
        result.setKeyGeneratorConfig(getKeyGeneratorConfiguration());
        return result;
    }


    /**
     * 定义sharding-Jdbc数据源
     */
    @Bean
    DataSource getShardingDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        Properties properties = new Properties();
        //配置sharding-jdbc打印sql
        properties.put("sql.show","true");
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig,properties);
    }
}
