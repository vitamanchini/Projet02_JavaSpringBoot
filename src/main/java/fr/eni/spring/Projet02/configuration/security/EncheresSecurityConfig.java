package fr.eni.spring.Projet02.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT pseudo, mot_de_passe, 1 FROM UTILISATEURS WHERE pseudo= ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.pseudo, r.role FROM UTILISATEURS u INNER JOIN ROLES r ON r.is_admin = u.administrateur WHERE u.pseudo= ?");
        return jdbcUserDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/users/signin").permitAll()
                            .requestMatchers("/users/profile").authenticated()
                            .requestMatchers("/users/modify").permitAll()
                            .requestMatchers("/users/modify/**").authenticated()
                            .requestMatchers("/css/**").permitAll()
                            .requestMatchers("/images/**").permitAll()
                            .anyRequest().authenticated();
                });


                http.formLogin(form ->{
                    form
                            .loginPage("/login").permitAll()
                            .defaultSuccessUrl("/accueil")
                            .permitAll();
                        });


                http.logout(logout ->{
                    logout
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .invalidateHttpSession(true)
                            .clearAuthentication(true)
                            .deleteCookies("JSESSIONID")
                            .logoutSuccessUrl("/accueil")
                            .permitAll();
                        });
                return http.build();
    }
}

