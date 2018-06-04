package com.RIA.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class SecurityConfig_Basic extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //Defino los usuarios que serán utilizados para acceder al endpoint "/service"
        //El prefijo '{noop}' del campo password corresponde a aclarar que la contraseña recibida no viene codificada
        //Ref: https://docs.spring.io/spring-security/site/docs/5.0.5.RELEASE/reference/htmlsingle/#pe-dpe
        auth.inMemoryAuthentication().withUser("ria").password("{noop}qwerty").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().
                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().csrf().disable().
                //Definido que el filtro de autenticación básica no aplica para el endpoint publicado en "/token"
                        authorizeRequests().antMatchers("/token/**", "/index/**").permitAll().anyRequest().authenticated();
    }
}