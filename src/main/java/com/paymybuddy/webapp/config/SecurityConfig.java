package com.paymybuddy.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The Class SecurityConfig.
 *
 * @author Senthil
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /** The user details service. */
	@Autowired
    private final UserDetailsService userDetailsService;


    /**
     * Instantiates a new security config.
     *
     * @param userDetailsServicee the user details servicee
     */
    public SecurityConfig(
    		final UserDetailsService userDetailsServicee) {
		this.userDetailsService = userDetailsServicee;
	}

 // ************************************************************************
    /**
     * Password encoder - an instance of BCryptPasswordEncoder
     *  to encrypt the password.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 // ************************************************************************
    /**
     * Configure the AuthenticationManagerBuilder to use the password encoder.
     *
     * @param auth AuthenticationManagerBuilder instance
     * @throws Exception the exception
     */
    @Override
    protected void configure(
    		final AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

 // ************************************************************************
    /**
     * Configure web based security for specific http requests.
     *
     * @param http the HttpSecurity
     * @throws Exception the exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/register*").permitAll();

        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/img/**").permitAll()
                    .antMatchers("/save**/**", "/admin,/manage/**")
                    	.hasRole("ADMIN")
                    .anyRequest().authenticated();

        http
        		.formLogin()
        		.loginPage("/login")
        		// redirect the user to the home page on login success
        		.defaultSuccessUrl("/home")
        		// redirect the user back to the login page on error login
        		.failureUrl("/login?error=true")
        		// redirect the user back to the registration page
        		// on error login
        		.failureForwardUrl("/register").permitAll();

//        http
//				.oauth2Login()
//        		.defaultSuccessUrl("/home", true)
//        		.loginPage("/login");

        // https://www.baeldung.com/spring-security-logout
        // https://www.baeldung.com/spring-security-manual-logout
        http
        		.logout()
        		// allows the session to be set up so that it's not
        		// invalidated (default)
        		.invalidateHttpSession(true)
        		// list of cookies to be deleted when the user logs out
        		.deleteCookies("JSESSIONID")
        		// Spring security default logout value
        		.logoutUrl("/logout")
        		.logoutSuccessUrl("/login");

        http
        		.exceptionHandling()
        		.accessDeniedPage("/notAuthorized");


        // Cross-Site Request Forgery (CSRF) is an attack that forces
        // authenticated users to submit a request to a Web application
        // against which they are currently authenticated. CSRF attacks
        // exploit the trust a Web application has in an authenticated user.

        // CSRF protection is enabled by default in the Java configuration.
        // https://stackoverflow.com/questions/52363487/what-is-the
        // -reason-to-disable-csrf-in-spring-boot-web-application

        // CSRF - disable it because it was interfering with our existing
        // authentication mechanism.
        http.csrf().disable();

    }
}
