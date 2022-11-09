package br.com.appbus.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/api/user").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/creditCard/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/creditCard/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/busTicket").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/busTicket/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/busTicket/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/bus").permitAll()
                .antMatchers(HttpMethod.GET, "/api/bus").permitAll()
                .antMatchers(HttpMethod.GET, "/api/bus/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/bus/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/bus/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/evaluation").permitAll()
                .antMatchers(HttpMethod.GET, "/api/evaluation").permitAll()
                .antMatchers(HttpMethod.GET, "/api/evaluation/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/evaluation/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/evaluation/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/**").permitAll()
                .antMatchers(HttpMethod.GET, "/h2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/h2/**").permitAll()
                .anyRequest().denyAll()
                .and()
                .csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
