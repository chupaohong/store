package com.store.bubostore;

import com.store.bubostore.service.user.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BuBoStoreApplication extends WebSecurityConfigurerAdapter {
    @Autowired
    UserLoginService userLoginService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(BuBoStoreApplication.class, args);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Turn off CSRF
        http.csrf().disable();

        // The page doesn't require login
        http.authorizeRequests()
                .antMatchers("/login", "/upload/**", "/register").permitAll()
                .antMatchers("/dashboard/**").hasAnyRole("ADMIN").anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=error")
                .and().exceptionHandling().accessDeniedPage("/access-denied")
                .and().logout().permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/assets/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService).passwordEncoder(bCryptPasswordEncoder());
    }
}