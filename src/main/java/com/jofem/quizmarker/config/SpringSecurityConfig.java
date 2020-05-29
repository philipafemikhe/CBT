package com.jofem.quizmarker.config;
//import com.main.general.services.security.CustomUserDetailService;
import com.jofem.quizmarker.Service.security.CustomUserDetailService;
import com.jofem.quizmarker.jwtsecurity.JwtAuthenticationEntryPoint;
import com.jofem.quizmarker.jwtsecurity.JwtAuthenticationProvider;
import com.jofem.quizmarker.jwtsecurity.JwtAuthenticationTokenFilter;
import com.jofem.quizmarker.jwtsecurity.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/secure/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                .loginProcessingUrl("/secure/users/login")
                .and()
                .logout()
                .logoutSuccessUrl("/logout")
                .and()
                .rememberMe()
                .tokenValiditySeconds(4838400)
                .key("workerKey");
    }

//    @Override
    // security based on url
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/secure/**").authenticated().and()
//                .authorizeRequests().antMatchers("/admin/**")
//                .authenticated().anyRequest().hasAnyRole("ADMIN").and()
//                .formLogin().permitAll();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/secure/**").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds(4838400)
//                .key("workerKey");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePSW());
    }

    @Bean
    public BCryptPasswordEncoder encodePSW(){
        return new BCryptPasswordEncoder();
    }




//
//    @Autowired
//    JwtAuthenticationProvider authenticationProvider;
//    @Autowired
//    private JwtAuthenticationEntryPoint entryPoint;
//
//    @Bean
//    public AuthenticationManager authenticationManager() {
//        return new ProviderManager(Collections.singletonList(authenticationProvider));
//    }
//
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
//        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
//        filter.setAuthenticationManager(authenticationManager());
//        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
//        return filter;
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests().antMatchers("**/rest/**").authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(entryPoint)
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.headers().cacheControl();
//
//    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("afemikhephilip@gmail.com").password("password").roles("Admin");
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("user");
//    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/v1/**");
//    }



    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public BCryptPasswordEncoder encodePSW(){
//        return new BCryptPasswordEncoder();
//    }
}
