package com.tsm.shardingjdbctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan("com.tsm.shardingjdbctest")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@ComponentScan("io.shardingsphere.transaction.aspect")
public class ShardingjdbcTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingjdbcTestApplication.class, args);
	}

}

