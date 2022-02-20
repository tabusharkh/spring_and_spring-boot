package com.testtask.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.testtask.spring.model", "com.testtask.spring.config", "com.testtask.spring.service",
	"com.testtask.spring.repository"})
public class WebAppConfig {

}
