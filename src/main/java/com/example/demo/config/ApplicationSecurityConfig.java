package com.example.demo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableConfigurationProperties
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final MySimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler;

    public ApplicationSecurityConfig(UserDetailsServiceImpl userDetailsService,
                                     MySimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.simpleUrlAuthenticationSuccessHandler = simpleUrlAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/registration", "/registration/*",
                        "/css/**", "/static/**", "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(simpleUrlAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
