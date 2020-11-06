package com.savetravel.SaveTravel;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	

@Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        
	httpSecurity.csrf().disable()
        .authorizeRequests()
        .antMatchers("/**").permitAll();
        
}
}

