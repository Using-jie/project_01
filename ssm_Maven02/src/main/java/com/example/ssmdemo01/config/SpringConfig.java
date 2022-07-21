package com.example.ssmdemo01.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author YongJ
 * @date 2022/6/15 11:36
 */
@Configuration
@ComponentScan({"com.example.ssmdemo01.service"})
@Import({JdbcConfig.class,MybatisConfig.class})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class SpringConfig {
}
