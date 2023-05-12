package com.project.ewdj;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
public class EwdjApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EwdjApplication.class, args);
	}

	// @Bean
	// LocaleResolver localResolver() {
	// SessionLocaleResolver slr = new SessionLocaleResolver();
	// slr.setDefaultLocale(Locale.forLanguageTag("NL"));
	// return slr;
	// }

	@Bean
	LocaleResolver localResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}

}
