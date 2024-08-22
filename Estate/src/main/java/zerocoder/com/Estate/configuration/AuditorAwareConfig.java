package zerocoder.com.Estate.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import zerocoder.com.Estate.utils.SecurityUtils;

@Configuration
@RequiredArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorAwareConfig {

    private final SecurityUtils securityUtils;

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new AuditorAwareImpl(securityUtils);
    }
}
