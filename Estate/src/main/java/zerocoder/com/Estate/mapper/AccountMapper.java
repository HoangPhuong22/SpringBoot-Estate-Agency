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
    public AccountResponse toAccountResponse(Account account, Integer type) {
        if(type == 0) {
            return AccountResponse.builder()
                    .username(account.getUsername())
                    .fullName(account.getCustomer().getFullName())
                    .email(account.getEmail())
                    .phone(account.getCustomer().getPhone())
                    .address(account.getCustomer().getAddress())
                    .build();
        } else {
            return AccountResponse.builder()
                    .username(account.getUsername())
                    .fullName(account.getEmployee().getFullName())
                    .email(account.getEmail())
                    .phone(account.getEmployee().getPhone())
                    .address(account.getEmployee().getAddress())
                    .status(account.getEmployee().getIsActive() ? "Đang làm việc" : "Đã nghỉ việc")
                    .build();
        }
    }
}
