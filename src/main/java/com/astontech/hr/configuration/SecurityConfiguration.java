package com.astontech.hr.configuration;

import org.apache.catalina.Group;
import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.authentication.method}")
    private String AUTH_METHOD;
    @Value("${spring.security.ldap.domain}")
    private String ldapDomain;
    @Value("${spring.security.ldap.url}")
    private String ldapUrl;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if (AUTH_METHOD.equals("NONE")) {

        }
        else if (AUTH_METHOD.equals("IN_MEMORY")) {
            auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
            auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN");
            auth.inMemoryAuthentication().withUser("dba").password("123").roles("DBA");
        } else if (AUTH_METHOD.equals("LDAP")) {
            auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
        } else if (AUTH_METHOD.equals("DATABASE")) {
            JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
            userDetailsService.setDataSource(dataSource);
            PasswordEncoder encoder = new BCryptPasswordEncoder();

            auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
            auth.jdbcAuthentication().dataSource(dataSource);

            if(!userDetailsService.userExists("user")){
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("USER"));
                User userDetails = new User("user", encoder.encode("password"), authorities);

                userDetailsService.createUser(userDetails);
            }
        }
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        if (AUTH_METHOD.equals("NONE")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/").permitAll()
                    .and()
                    .authorizeRequests().antMatchers("/console").permitAll();
        }
        else if(AUTH_METHOD.equals("IN_MEMORY")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .and()
                    .authorizeRequests().antMatchers("/console/**").access("hasRole('ROLE_DBA')");

        } else if (AUTH_METHOD.equals("LDAP")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/static/**").permitAll()
                    .and().authorizeRequests().antMatchers("/login**").permitAll()
                    .and().authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated();
        } else if (AUTH_METHOD.equals("DATABASE")) {
            httpSecurity
                    .authorizeRequests().antMatchers("/static/**").permitAll()
                    .and().authorizeRequests().antMatchers("/login**").permitAll()
                    .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("USER")
                    .anyRequest().authenticated();
        }
        httpSecurity
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/login.do")
                .defaultSuccessUrl("/", true).failureUrl("/login?err=1")
                .usernameParameter("username").passwordParameter("password");

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider authenticationProvider = new ActiveDirectoryLdapAuthenticationProvider(ldapDomain, ldapUrl);
        authenticationProvider.setConvertSubErrorCodesToExceptions(true);
        authenticationProvider.setUseAuthenticationRequestCredentials(true);

        return authenticationProvider;
    }
}
