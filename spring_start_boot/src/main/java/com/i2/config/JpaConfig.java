package com.i2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(value="com.i2.interfaces")		//value指定数据访问层接口的位置
public class JpaConfig {
//配置datasource，platformtransactionmanager等参数
}
