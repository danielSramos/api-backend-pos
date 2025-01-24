package br.com.unifap.pos.atv_sinc1.setup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
    * Classe de configurações de segurança.
    *
    * Aqui são configurados os parâmetros de segurança como endpoints protegidos e públicos.
    * A configuração atual está liberando todos os endpoints para aceesso público, mas pode ser alterado quando necessário.
    *
    * */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll());

        http.headers(headers -> headers
                .frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
