package com.kozik.PKPTicket.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()).rolePrefix("ROLE_")
                .dataSource(dataSource)
                .usersByUsernameQuery("select email as principal, haslo as credentials, enabled from uzytkownik where email=?")
                .authoritiesByUsernameQuery("select uzytkownik_email as principal, uprawnienia_nazwa as role from uzytkownik_uprawnienia where uzytkownik_email = ? ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/register", "/", "/home", "/login", "/kursy", "/css/**", "/js/**","/img/**").permitAll()
                .antMatchers("/profile").hasAnyRole("user,admin,maszynista")
                .antMatchers("/adres/**", "/bilet/**", "/kurs/**", "/maszynista/**","/pasazer/**","/pociag/**","/uzytkownik/**", "znizka/**").hasAnyRole("admin")
                .antMatchers("/klient/**").hasAnyRole("user")
                .antMatchers("/obsluga/**").hasAnyRole("maszynista,admin")
                .and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/profile").and().logout().logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
