package com.tsm.shardingjdbctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tsm.shardingjdbctest")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ShardingjdbcTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingjdbcTestApplication.class, args);
	}

}

