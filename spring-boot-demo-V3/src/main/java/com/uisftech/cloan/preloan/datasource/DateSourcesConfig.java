package com.uisftech.cloan.preloan.datasource;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 多数据源配置
 * @author wangwei
 *
 */
@Configuration
public class DateSourcesConfig {

	@Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

	@Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.secondary")
	public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
