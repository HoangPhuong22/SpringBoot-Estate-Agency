package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.AccountRequest;

public interface AccountService {
    Long saveAccount(AccountRequest request);
    String getUserName(Long id);
    void changePassword(Long id, String password);
}
