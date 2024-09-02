package zerocoder.com.Estate.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerocoder.com.Estate.utils.SecurityUtils;

@RequestMapping("/auth")
@RequiredArgsConstructor
@Controller(value = "AuthenticationController")
public class AuthenticationController {

    private final SecurityUtils securityUtils;

    @GetMapping("/login")
    public String login() {
        if(securityUtils.isAuthenticated()) {
            return "redirect:/admin/profile";
        }
        return "auth/login";
    }
}
