package com.aprox.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
            Use this code to generate new passwords

            String encoded=new BCryptPasswordEncoder().encode("123456");
            System.out.println(encoded);
        */

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/authenticate", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                //.antMatchers("/api/**").hasRole("API")
                //.antMatchers("/home").permitAll()
                //.antMatchers("/**").permitAll()
                //.and().httpBasic().
                //and().logout().logoutUrl("/logout")
                //.deleteCookies("JSESSIONID");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}