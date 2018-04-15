package com.bhz.mail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc   // 启用spring mvc
@Configuration  // 让springboot项目启动时识别当前配置类
@ComponentScan({"com.bhz.mail.*"}) // 包扫描
@MapperScan(basePackages = "com.bhz.mail.mapper") // 扫描DAO
//@ImportResource({""}) // 引入自定义配置文件
public class MainConfig {
}
