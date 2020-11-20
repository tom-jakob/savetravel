
package com.savetravel.SaveTravel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.savetravel.SaveTravel.Security.Services.UserDetailsServiceImpl;
import com.savetravel.SaveTravel.Security.jwt.AuthEntryPointJwt;
import com.savetravel.SaveTravel.Security.jwt.AuthTokenFilter;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true) 
@EnableGlobalMethodSecurity(
//		 securedEnabled = true,
//		 jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	//? implements WebMvcConfigurer??
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerbuilder) throws Exception {
		authenticationManagerbuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	// TODO harmonize Cors & co
	
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*")
                .maxAge(3600L)
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true);
    }
    

   	
    	
    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
    		http.cors().and().csrf().disable()
    			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
    			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    			.authorizeRequests().antMatchers("/**").permitAll()
//    			.antMatchers("/test/user").hasRole("USER")
    			.antMatchers("/test/mod").hasRole("MODERATOR")
    			.antMatchers("/test/admin").hasRole("ADMIN")
    			.anyRequest().authenticated();

    		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
          	http.headers().frameOptions().disable();
    	
      	
   
	  }
}


