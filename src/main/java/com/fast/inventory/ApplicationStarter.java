package com.fast.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.Banner;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class ApplicationStarter {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationStarter.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
		// SpringApplication.run(DashboardApplication.class, args);
	}
}
