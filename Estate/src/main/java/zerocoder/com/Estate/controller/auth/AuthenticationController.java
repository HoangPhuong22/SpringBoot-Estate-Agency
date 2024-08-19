package zerocoder.com.Estate.controller.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller(value = "AuthenticationController")
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
