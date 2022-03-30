package com.tasneem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure (HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.headers().frameOptions().disable();
    http.authorizeRequests()
        .antMatchers("/secure/man/**").access("hasRole('managers')")
        .antMatchers("secure/emp/**").access("hasRole('employees')")
        .and().formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/appLogin")
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/secure/emp")
        .and().logout()
        .logoutUrl("appLogout")
        .logoutSuccessUrl("/appLogout")
        .and().exceptionHandling()
        .accessDeniedPage("/accessDenied");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.ldapAuthentication()
        .userDnPatterns("uid={0},ou=people")
        .userSearchBase("ou=people")
        .userSearchFilter("uid={0}")
        .groupSearchBase("ou=groups")
        .groupSearchFilter("uniqueMember={0}")
        .contextSource()
        .url("ldap://localhost:2389/dc=tasneemtask,dc=com")
        .and()
        .passwordCompare()
        //.passwordEncoder(passwordEncoder())
        .passwordAttribute("userPassword");
    //$2a$10$aY9CoYk85i9jzppW9jxDE.E3wxEY3hT2jFsEF4MWymVtxzErwPfP
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder;
  }
}
