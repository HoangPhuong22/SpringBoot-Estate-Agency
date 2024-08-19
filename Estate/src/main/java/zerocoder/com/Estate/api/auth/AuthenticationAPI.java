package zerocoder.com.Estate.api.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.SignInRequest;
import zerocoder.com.Estate.dto.response.TokenResponse;
import zerocoder.com.Estate.service.AuthenticationService;

@Slf4j
@Validated
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController(value = "AuthenticationAPI")
public class AuthenticationAPI {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody SignInRequest signInRequest) {
        return new ResponseEntity<>( authenticationService.authenticate(signInRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(HttpServletRequest request) {
        String token = request.getHeader("x-token");
        log.info("Token: {}", token);
        return new ResponseEntity<>(authenticationService.refresh(request), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public String logout() {
        return "success";
    }
}
