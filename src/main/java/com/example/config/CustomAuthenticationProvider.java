package com.example.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.domainUser.service.impl.CustomUserDetails;

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
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // validationフィールドが2の場合はログイン不可
        if (userDetails instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            if (customUserDetails.getValidation() == 2) {
                throw new CustomValidationException("システム管理者へ問い合わせてください。");
            }
        }
        
        
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
        throw new BadCredentialsException("ログインIDもしくはパスワードが間違っています。");
    }
   }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
    
} 
