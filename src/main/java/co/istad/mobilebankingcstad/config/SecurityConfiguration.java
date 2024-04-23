package co.istad.mobilebankingcstad.config;

import co.istad.mobilebankingcstad.security.CustomUserDetailService;
import co.istad.mobilebankingcstad.security.JwtToUserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomUserDetailService userDetailService;
    private final JwtToUserConverter jwtToUserConverter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(
                        (auth) -> auth.requestMatchers("/",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/v2/api-docs/**",
                                        "/swagger-resource/**")
                                .permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/users/**").permitAll()
                                .requestMatchers("api/v1/files/**", "images/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)//*disable default login form
                .httpBasic(Customizer.withDefaults())//*basic auth
                .oauth2ResourceServer(
                        (oauth2)->oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtToUserConverter))
                )
                .sessionManagement(
                        (session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex->ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                ).build();
    }
}
