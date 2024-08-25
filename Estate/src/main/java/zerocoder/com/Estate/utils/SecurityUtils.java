package zerocoder.com.Estate.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.model.Account;

@Component
public class SecurityUtils {

    public Account getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()) {
            return (Account) authentication.getPrincipal();
        } else {
            return null;
        }
    }
    public String getRole() {
        return getPrincipal().getRoles().stream().findFirst().get().getName();
    }
}
