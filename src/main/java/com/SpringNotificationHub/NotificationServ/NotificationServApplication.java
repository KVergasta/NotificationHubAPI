package com.SpringNotificationHub.NotificationServ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.SpringNotificationHub"})
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NotificationServApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServApplication.class, args);
	}

}
