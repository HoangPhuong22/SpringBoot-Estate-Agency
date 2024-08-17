package zerocoder.com.Estate.mapper;

import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.model.Account;

@Component
public class AccountMapper {
    public Account toAccount(AccountRequest request) {
        return Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
