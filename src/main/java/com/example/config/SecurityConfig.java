package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

//@EnableWebSecurityをつけることで、Spring Securityのウェブセキュリティサポートを有効化する
@EnableWebSecurity
//クラスに@Configurationをつけることで、このクラスがSpringの設定クラスであることを示す
@Configuration
public class SecurityConfig {
    
    // CustomAuthenticationProvider Beanをこのクラスに注入する
    private final CustomAuthenticationProvider customAuthenticationProvider;
    
    public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
    	this.customAuthenticationProvider = customAuthenticationProvider;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     
		//ログイン不要ページの区分け
		http     
		// カスタム認証プロバイダを設定
        .authenticationProvider(customAuthenticationProvider)
//        // CORSの設定を適用
//        .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
//        // CSRFの保護を無効にする
//        .csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
    		 .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
    		 .requestMatchers("/error/**").permitAll()
    		 .requestMatchers("/login/*").permitAll()
    		 .requestMatchers("/user/**").hasAuthority("GENERAL")
    		 .requestMatchers("/admin/**").hasAuthority("ADMIN")
    		 .anyRequest().authenticated()
	   ).formLogin(login -> login
			 .loginProcessingUrl("/login")//ログイン処理のパス
			 .loginPage("/login/login")//ログインページの指定
		     .defaultSuccessUrl("/admin/list")//成功時の遷移先
		     .failureUrl("/login?error")
	   );
		
         return http.build();
	}	
	
    // @Beanをつけることで、このメソッドがSpringのコンテナにBeanとして登録される
//    @Bean
//    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//        // CORSの設定を行うためのオブジェクトを生成
//        //CorsConfiguration configuration = new CorsConfiguration();
//
//        // クレデンシャル（資格情報（CookieやHTTP認証情報））を含むリクエストを許可する
//        //configuration.setAllowCredentials(true);
//
//        // 許可するオリジン（この場合は"http://127.0.0.1:5500"のみ）を設定
//        //configuration.addAllowedOrigin("http://127.0.0.1:5500");
//
//        // 任意のヘッダーを許可
//        //configuration.addAllowedHeader("*");
//
//        // 任意のHTTPメソッド（GET, POSTなど）を許可
//        //configuration.addAllowedMethod("*");
//
//        // CORS設定をURLベースで行うためのオブジェクトを生成
//        //UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        // 全てのURLパスにこのCORS設定を適用
//        //source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

	
}
	       
