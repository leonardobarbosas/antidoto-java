
package org.example.antidoto.config;

import org.example.antidoto.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtFilter
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Autenticação e cadastro
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // Animais: consulta pública, gerenciamento ADMIN
                        .requestMatchers(HttpMethod.GET, "/animais", "/animais/**").permitAll()
                        .requestMatchers("/animais", "/animais/**").hasRole("ADMIN")

                        // Acidentes: cadastro e consulta por usuário
                        .requestMatchers(HttpMethod.POST, "/acidentes").authenticated()
                        .requestMatchers(HttpMethod.GET, "/acidentes/usuario/**").authenticated()

                        // Demais operações de acidentes: ADMIN
                        .requestMatchers("/acidentes", "/acidentes/**").hasRole("ADMIN")

                        // Gerenciamento de usuários: ADMIN
                        .requestMatchers("/usuarios", "/usuarios/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/ia/orientacoes").authenticated()

                        .anyRequest().authenticated()
                )


                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
