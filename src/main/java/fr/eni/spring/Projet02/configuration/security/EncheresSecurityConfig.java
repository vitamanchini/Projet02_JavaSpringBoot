package fr.eni.spring.Projet02.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class EncheresSecurityConfig {

    private static final String SELECT_USER = "SELECT pseudo, password, 1 FROM UTILISATEURS WHERE pseudo= ?";
    private static final String SELECT_ROLES = "SELECT u.pseudo, r.role FROM UTILISATEURS u INNER JOIN ROLES r ON r.is_admin = u.admin WHERE u.pseudo= ?";

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
        return jdbcUserDetailsManager;
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers(HttpMethod.GET, "/").permitAll()
                    .requestMatchers(HttpMethod.GET,"/").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/css/*").permitAll()
                    .requestMatchers("/images/*").permitAll()
                    .anyRequest().authenticated();
        });

        http.formLogin(form -> {
            form
                    .loginPage("/").permitAll()
                    .defaultSuccessUrl("/session").permitAll();

        });

        http.logout(logout -> {
            logout
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
                    .permitAll();
        });

        return http.build();
    }
}
