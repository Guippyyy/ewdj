package com.project.ewdj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import domein.AlbumExpertBean;
import domein.ExpertBean;

@SpringBootApplication
public class EwdjApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EwdjApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/welcome");
	}

	@Bean
	ExpertBean expertBean() {
		return new AlbumExpertBean();
	}

}
