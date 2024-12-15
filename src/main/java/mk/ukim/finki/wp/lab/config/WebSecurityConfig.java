package mk.ukim.finki.wp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin")) // Predefined admin password
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .requestMatchers("/login").permitAll()
//                .requestMatchers("/**").authenticated()
//                .and()
//                .formLogin().permitAll();
//        return http.build();
//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("*/add", "*/edit", "*/delete").hasRole("ADMIN") // Restricted pages
                        .anyRequest().permitAll() // All other pages are publicly accessible
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/events", true) // Redirect to /events after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/") // Redirect to home page after logout
                        .permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable); // Disable CSRF for simplicity (enable in production with proper configuration)

        return http.build();
    }

}
