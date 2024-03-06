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

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
//	public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//		this.userDetailsService = userDetailsService;
//		this.passwordEncoder = passwordEncoder;
//	}
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //ブラウザから入力したユーザ名・パスワードを取得
    	String loginId = authentication.getName();
        String password = (String) authentication.getCredentials();        
    	
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginId);
        System.out.println(userDetails);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(loginId, password, userDetails.getAuthorities());
        } else {
    	// TODO Auto-generated method stub
        throw new BadCredentialsException("Authentication failed");
    }
   }

    @Override
    public boolean supports(Class<?> authentication) {
        // authentication(認証方式)がUsernamePasswordAuthenticationToken.class(ユーザー名とパスワード認証)か判定
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
} 
