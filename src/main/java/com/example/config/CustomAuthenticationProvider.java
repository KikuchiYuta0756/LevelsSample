package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//Componentを付与することで、このクラスがSpringのコンテナにBeanとして登録される。
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;

	private final PasswordEncoder passwordEncoder;
	
	
	public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //ブラウザから入力したユーザ名・パスワードを取得
    	String username = authentication.getName();
        String password = (String) authentication.getCredentials();   
        System.out.println("usernameの値は:" + username);
        System.out.println("passwordの値は:" + password);
        
        
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("userDetailsの値は:" + userDetails);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
    	// Auto-generated method stub
        throw new BadCredentialsException("Authentication failed");
    }
   }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean supported = authentication.equals(UsernamePasswordAuthenticationToken.class);
        System.out.println("Supported authenticationの結果は: " + supported);
        return supported;
    	// authentication(認証方式)がUsernamePasswordAuthenticationToken.class(ユーザー名とパスワード認証)か判定
        //return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
} 