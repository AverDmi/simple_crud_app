package com.dimthomas.telros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login");
    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$0YXpKp6wK9Aqh69bL1zkKeejafKx8aqldkHV.sMoGlVYv841q/p96")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$0YXpKp6wK9Aqh69bL1zkKeejafKx8aqldkHV.sMoGlVYv841q/p96")
//                .roles("ADMIN")
//                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(admin);
        return users;
    }
}
