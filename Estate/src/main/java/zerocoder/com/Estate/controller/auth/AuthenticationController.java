package zerocoder.com.Estate.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller(value = "AuthenticationController")
public class AuthenticationController {

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
