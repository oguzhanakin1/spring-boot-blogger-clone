package com.spring.bloggerclone.security;

import com.spring.bloggerclone.security.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/authentication/**").permitAll()
                .anyRequest().authenticated();
        //http.formLogin().loginProcessingUrl("/login").failureUrl("/login.html?loginFailed=true");// just a login page to see if web driver gets the jwt automatically
        //http.rememberMe().userDetailsService(customUserDetailsService);
        http.httpBasic();
        http.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);// filters the request by token
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter()
    {
        return new JwtAuthorizationFilter();
    }

    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedOrigins("*")//localhost, "^http://([-\\w]*.)?localhost(:[0-9]*?" localhost kısıtlaması için gerekli örnek regex
                        .allowedMethods("*");// POST,GET,...
            }
        };
    }
}
