package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zerocoder.com.Estate.dto.request.AccountEditRequest;
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
    public ResponseData<?> addAccount(@Valid @ModelAttribute AccountRequest request, @RequestParam("avatar") MultipartFile avatar) {
        Long id = accountService.saveAccount(request, avatar);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Add account successfully", id);
    }

    @PutMapping("/edit")
    public ResponseData<?> editAccount(@Valid @ModelAttribute AccountEditRequest request, @RequestParam("avatar") MultipartFile avatar) {
        Long id = accountService.editAccount(request, avatar);
        return new ResponseData<>(HttpStatus.OK.value(), "Edit account successfully", id);
    }

    @PatchMapping("/change-password")
    public ResponseData<?> updateAccount(@Valid @RequestBody ChangePasswordRequest request) {
        accountService.changePassword(request.getId(), request.getPasswordChange());
        return new ResponseData<>(HttpStatus.OK.value(), "Change password successfully");
    }

    @PatchMapping("/edit-delete/{id}")
    public ResponseData<?> editDelete(@PathVariable Long id) {
        accountService.editDelete(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Edit delete successfully");
    }
}
