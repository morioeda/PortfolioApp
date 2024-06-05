package com.example.portfolioapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.portfolioapp.dto.SkillAddRequest;

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
	               )
	            
	            .logout((logout)-> logout
	       	         .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //
	       	         .logoutUrl("/logout") //ログアウトのURL
	       	         .logoutSuccessUrl("/user/login") //ログアウト成功後のURL
	       	         .deleteCookies("JSESSIONID")
	       	         .invalidateHttpSession(true).permitAll()
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
	
	//新規登録後のログイン認証用※上手く動いてないっぽい
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
			return authenticationConfiguration.getAuthenticationManager();
		}
	
}