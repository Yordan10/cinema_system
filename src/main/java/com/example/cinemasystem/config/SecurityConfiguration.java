package com.example.cinemasystem.config;

import com.example.cinemasystem.service.AuthenticationUserDetailService;
import com.example.cinemasystem.filter.JWTAuthenticationFilter;
import com.example.cinemasystem.filter.JWTAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailService authenticationUserDetailService;

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and().csrf().disable();
        http.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                //ROLE BASED AUTHENTICATION START
            //    .antMatchers("/account").hasAnyAuthority("USER")
                .antMatchers("/account/*").permitAll()
                .antMatchers("/account").permitAll()
                .antMatchers("/movies/upload/photo/*").hasAnyAuthority("ADMIN")
                .antMatchers("/movies/**/**").permitAll()//hasAnyAuthority("USER")
                .antMatchers("/movies/**").permitAll()//hasAnyAuthority("USER")
                .antMatchers("/movies").permitAll()//hasAnyAuthority("USER")// HAS TO CHANGE ------------------
                .antMatchers("/reservation").permitAll()
                .antMatchers(HttpMethod.DELETE,"/movies/delete").permitAll()
                //ROLE BASED AUTHENTICATION END
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }
}
