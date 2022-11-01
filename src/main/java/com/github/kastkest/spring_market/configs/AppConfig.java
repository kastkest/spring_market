package com.github.kastkest.spring_market.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secrets.properties")
@EnableAspectJAutoProxy
@ComponentScan("com.github.kastkest.spring_market")
public class AppConfig {
}
