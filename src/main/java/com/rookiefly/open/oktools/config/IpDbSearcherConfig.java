package com.rookiefly.open.oktools.config;

import org.apache.commons.io.FileUtils;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @Classname IpDbSearcherConfig
 * @Description TODO
 * @Date 2021/3/23 5:46 下午
 * @Created by rookiefly
 */
@Configuration
public class IpDbSearcherConfig {

    @Bean
    public DbSearcher dbSearcher() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("ip2region.db");
        File tempFile = File.createTempFile("tmp_ip2region", ".db");
        FileUtils.copyInputStreamToFile(classPathResource.getInputStream(), tempFile);
        DbConfig dbConfig = new DbConfig();
        return new DbSearcher(dbConfig, tempFile.getPath());
    }
}
