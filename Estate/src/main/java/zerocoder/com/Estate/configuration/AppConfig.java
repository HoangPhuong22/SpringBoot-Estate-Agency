package zerocoder.com.Estate.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import zerocoder.com.Estate.service.CustomUserDetailService;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailService userDetailsService;
    private final FailureHandler failureHandler;

    private String[] WHITE_LIST = {"/", "/auth/**", "/admin/assets/**", "/user/assets/**"};
    private String[] ADMIN_LIST = {
            "/api/employee/**",
            "/admin/employee/**",
            "/api/property/delete/**",
            "/api/amenity/**",
            "/admin/amenity/add",
            "/admin/amenity/edit/**",
    };
    private String[] MANAGER_LIST = {
            "/api/customer/**",
            "/api/property/edit/**",
            "/api/property/add/**",
            "/admin/customer/**",
            "/admin/amenity"
    };
    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers(WHITE_LIST).permitAll()
                    .requestMatchers(ADMIN_LIST).hasAuthority("ADMIN")
                    .requestMatchers(MANAGER_LIST).hasAnyAuthority("ADMIN", "EMPLOYEE")
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin ->
                formLogin
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth-login")
                    .failureHandler(failureHandler)
                    .permitAll())
            .logout(logout ->
                logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll())
        ;
        return http.build();
    }

    @Bean
    public AuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
