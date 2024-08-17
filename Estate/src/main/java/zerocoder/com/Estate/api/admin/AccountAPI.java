package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.dto.request.ChangePasswordRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.AccountService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "AccountAPIAdmin")
@RequestMapping("/api/account")
public class AccountAPI {

    private final AccountService accountService;

    @PostMapping("/add")
    public ResponseData<?> addAccount(@Valid @RequestBody AccountRequest request) {
        Long id = accountService.saveAccount(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Add account successfully", id);
    }

    @PatchMapping("/change-password")
    public ResponseData<?> updateAccount(@Valid @RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request.getId(), request.getPasswordChange());
        return new ResponseData<>(HttpStatus.OK.value(), "Change password successfully");
    }
}
