package com.example.portfolioapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(authorize -> authorize
	                .requestMatchers("/").permitAll()
	                .requestMatchers("/user/login","/user/signin").permitAll()
	                .requestMatchers("/css/**").permitAll()
	                .anyRequest().authenticated()
	            )
	            .formLogin(form -> form
	            	.usernameParameter("email")
	            	.passwordParameter("password")
	                .loginPage("/user/login")
	                .defaultSuccessUrl("/user/top", true)
	                
	            );
	            

	        return http.build();
	    }
	  
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        // UserDetailsServiceを設定し、ユーザー認証情報を提供
	        auth.userDetailsService(userDetailsService)
	                // パスワードエンコードを行わない設定
	                .passwordEncoder(NoOpPasswordEncoder.getInstance());
	    }
	  
	  
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}