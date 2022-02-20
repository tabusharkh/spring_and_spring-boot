package com.data.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.data.controller.DataController;

@SpringBootApplication
@ComponentScan(basePackageClasses=DataController.class)
@ComponentScan({"com.data.controller", "com.data.service", "com.data.serviceImpl", "com.data.validate"})
@EntityScan("com.data.entity")
@EnableJpaRepositories("com.data.repo")
//@ComponentScan({"com.data.entity", "com.data.service", "com.data.serviceImpl", "com.data.validate"})
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}
//
//(scanBasePackages={
//		"com.data.controller", "com.data.entry", "com.data.service", "com.data.serviceImpl", "com.data.validate"})