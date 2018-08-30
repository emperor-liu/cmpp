package com.lljqiu.tools.cmpp.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ="com.lljqiu.tools.cmpp.client")
public class CmppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmppClientApplication.class, args);
	}
}
