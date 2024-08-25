package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.dto.response.AccountResponse;

public interface AccountService {
    Long saveAccount(AccountRequest request);
    String getUserName(Long id);
    void changePassword(Long id, String password);
}
