package com.example.portfolioapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.csrf().disable();
		http.authorizeHttpRequests(authorize ->{
			authorize
			.requestMatchers("/").permitAll()
			.requestMatchers("/user/**").permitAll()//userフォルダ配下のものは全て認証無にアクセス可
			.requestMatchers("/css/**").permitAll()
			.anyRequest().authenticated();
			
		});
		return http.build();
	}
	
		
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}