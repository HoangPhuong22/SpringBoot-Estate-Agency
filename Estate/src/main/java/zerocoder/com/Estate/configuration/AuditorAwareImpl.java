package zerocoder.com.Estate.configuration;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    private final SecurityUtils securityUtils;

    public AuditorAwareImpl(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        Account account = securityUtils.getPrincipal();
        if(account == null) {
            return Optional.empty();
        }
        return Optional.of(account.getId());
    }
}
