package com.rookiefly.open.oktools.config;

import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname IpDbSearcherConfig
 * @Description TODO
 * @Date 2021/3/23 5:46 下午
 * @Created by rookiefly
 */
@Configuration
public class IpDbSearcherConfig {

    @Value("${oktools.ip2region-db-path:/Users/rookiefly/oktools/data/ip2region.db}")
    private String dbPath;

    @Bean
    public DbSearcher dbSearcher() throws Exception {
        DbConfig dbConfig = new DbConfig();
        return new DbSearcher(dbConfig, dbPath);
    }
}
