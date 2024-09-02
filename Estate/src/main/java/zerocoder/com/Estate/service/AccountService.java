package zerocoder.com.Estate.service;

import org.springframework.web.multipart.MultipartFile;
import zerocoder.com.Estate.dto.request.AccountEditRequest;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {
    Long saveAccount(AccountRequest request, MultipartFile avatar);
    Long editAccount(AccountEditRequest request, MultipartFile avatar);
    String getUserName(Long id);
    void changePassword(Long id, String password);
    void editDelete(Long id);
    List<AccountResponse> findAllByRole(String role);
}
