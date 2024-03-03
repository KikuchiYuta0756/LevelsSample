package com.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     
		//ログイン不要ページの区分け
		http.authorizeHttpRequests((authorize) -> authorize
    		 .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
    		 .requestMatchers("/error/**").permitAll()
    		 .requestMatchers("/login/*").permitAll()
    		 .anyRequest().authenticated()
		   ).formLogin(login -> login
		     .loginProcessingUrl("/login")//ログイン処理のパス
		     .loginPage("/login")//ログインページの指定
		     .defaultSuccessUrl("/admin/list")//成功時の遷移先
		     .failureUrl("/login?error")
		     .usernameParameter("username")//ログインページのユーザID
		     .passwordParameter("password")//ログインページのパスワード
		     .permitAll()
		     
		);
		
     return http.build();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }	
	
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .authorities("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
	
}
	       
