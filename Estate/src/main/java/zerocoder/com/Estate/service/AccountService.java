package zerocoder.com.Estate.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import zerocoder.com.Estate.dto.request.AccountRequest;

public interface AccountService {

    UserDetailsService userDetailsService();

    Long saveAccount(AccountRequest request);
    void changePassword(Long id, String password);
}
