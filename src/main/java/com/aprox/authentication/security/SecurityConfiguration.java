package com.aprox.authentication.security;

import com.aprox.authentication.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.util.DigestUtils;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, active"
                        + " FROM user WHERE email=?")
                .authoritiesByUsernameQuery("SELECT email, role FROM `user` " +
                        "INNER JOIN user_role ON user_role.user_id = `user`.id " +
                        "INNER JOIN role ON role.id = user_role.role_id " +
                        "WHERE `user`.email = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
            User this code to generate new passwords
         */
        String encoded=new BCryptPasswordEncoder().encode("123456");
        System.out.println(encoded);

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api").hasRole("API")
                .antMatchers("/home").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic().
                and().logout().logoutUrl("/logout")
                .deleteCookies("JSESSIONID");
    }
}