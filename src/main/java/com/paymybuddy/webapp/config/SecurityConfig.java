package com.paymybuddy.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	// **************************** TODOs LIST ***********************************
	
	// Method: configure(HttpSecurity http)
	// --> http.authorizeRequests() list of web resources and accessible endpoints with antMatchers
	// --> http.XXX(): configure routing of login, logout, home, exceptionHandler Urls
	// --> http.csrf.disable() : configuration against cross site request forgery
	
	// **************************** TODOs LIST ***********************************
	
	// Method: configure(HttpSecurity http)
	// --> PasswordEncryption using Encoders like BCrypt
	//
	// REFERENCES AND GUIDES
	// ---------------------
	// --> https://www.baeldung.com/java-config-spring-security
	// --> SecurityConfig extends WebSecurityConfigurerAdapter [Web Security With Java Configuration]
	// --> PasswordEncoder passwordEncoder() 
	// --> configure(HttpSecurity http) [HTTP Security]
	// --> configure Form Login [http.authorizeRequests()]
	// --> Authorization With Roles [http.authorizeRequests() = > .antMatchers]
	// --> Logout http.logout() configuration
	// --> Authentication: configureGlobal(AuthenticationManagerBuilder auth) 
	// --> 
	// --> https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
	// --> https://howtodoinjava.com/spring5/security/security-java-config-enablewebsecurity-example/
	// --> https://medium.com/@er.rameshkatiyar/configure-spring-security-in-your-application-ae303fa78959

	
	// for testing initially i am deactivating the basic spring security login page
	// https://stackoverflow.com/questions/23636368/how-to-disable-spring-security-login-screen
//	@Override
//    protected void configure(HttpSecurity security) throws Exception
//    {
//     security.httpBasic().disable();
//    }
	
	// https://stackoverflow.com/questions/44629289/spring-security-disable-default-login-page/44629660
	//  disable the default login page
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS,"*/").permitAll()
                .antMatchers(HttpMethod.GET,"/login").permitAll();
    }
}
