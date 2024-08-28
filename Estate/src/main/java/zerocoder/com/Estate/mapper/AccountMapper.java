package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.dto.response.AccountResponse;
import zerocoder.com.Estate.model.Account;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    public Account toAccount(AccountRequest request) {
        return Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
    public AccountResponse toAccountResponse(Account account) {
        if(account.getEmployee() != null) {
            return AccountResponse.builder()
                    .id(account.getId())
                    .username(account.getUsername())
                    .email(account.getEmail())
                    .status(account.getEmployee().getIsActive() ? "Đang làm việc" : "Đã nghỉ việc")
                    .roles(account.getRoles().stream().findFirst().get().getName())
                    .isDeleted(account.getIsDeleted())
                    .build();
        } else {
            return AccountResponse.builder()
                    .id(account.getId())
                    .username(account.getUsername())
                    .email(account.getEmail())
                    .roles(account.getRoles().stream().findFirst().get().getName())
                    .isDeleted(account.getIsDeleted())
                    .build();
        }
    }
}
