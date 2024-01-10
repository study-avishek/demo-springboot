package com.increff.springboot.demo;

import com.increff.account.client.AuthClient;
import com.increff.account.client.AuthTokenFilter;
import com.increff.account.client.CredentialFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
@Order(-1)
public class SecurityConfiguration {
    @Autowired
    private AuthClient authClient;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**", "/app/**")
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/admin/**").hasAnyAuthority("admin", "app.admin")
                        .requestMatchers("/app/**").hasAnyAuthority("admin", "app.admin","app.standard")
                        .anyRequest().authenticated())//
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAfter(new CredentialFilter(authClient), BasicAuthenticationFilter.class)
                .addFilterBefore(new AuthTokenFilter(authClient), CredentialFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/public/**", "/swagger-ui/**", "/public/**", "/v3/api-docs/**", "/session/**", "/actuator/**");
    }
}

