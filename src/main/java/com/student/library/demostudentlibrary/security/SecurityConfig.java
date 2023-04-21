package com.student.library.demostudentlibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
    // /student/ -> get (ADMIN/ STUDENT) , put STUDENT ADMIN, delete ADMIN, POST ADMIN
    // /book/ -> GET STUDENT ADMIN, POST/PUT/DELETE ADMIN
    // /transaction/ (issue or return) -> GET ADMIN STUDENT, POST PUT DELETE ADMIN
    // /author/ no authentication or authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                //STUDENT APIs
                .antMatchers(HttpMethod.GET,"/student/getStudent/**").hasAnyAuthority(AuthorityConstants.STUDENT_ATHORITY, AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers(HttpMethod.GET, "/student/getStudents/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers(HttpMethod.PUT, "/student/update/**").hasAnyAuthority(AuthorityConstants.STUDENT_ATHORITY, AuthorityConstants.ADMIN_ATHORITY)
                //minimize below stmts to one liner
                //.antMatchers(HttpMethod.POST, "/student/create/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                //.antMatchers(HttpMethod.DELETE, "/student/delete/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers("/student/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                //BOOK APIs
                .antMatchers(HttpMethod.GET, "/book/**").hasAnyAuthority(AuthorityConstants.STUDENT_ATHORITY, AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers("/book/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                //TRANSACTION APIs
                .antMatchers(HttpMethod.GET, "/transaction/get").hasAnyAuthority(AuthorityConstants.STUDENT_ATHORITY, AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers("/transaction/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                //AUTHOR APIs
                .antMatchers(HttpMethod.GET,"/author/**").hasAnyAuthority(AuthorityConstants.STUDENT_ATHORITY, AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers("/author/**").hasAuthority(AuthorityConstants.ADMIN_ATHORITY)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
