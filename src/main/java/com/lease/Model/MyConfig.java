package com.lease.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MyConfig {

	@Value("${imgAddress}")
	public String imgAddress;
}
