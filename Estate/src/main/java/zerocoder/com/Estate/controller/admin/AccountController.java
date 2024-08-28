package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.utils.SecurityUtils;

@Slf4j
@RequiredArgsConstructor
@Controller(value = "AccountControllerAdmin")
@RequestMapping(value = "/admin")
public class AccountController {

    private final SecurityUtils securityUtils;
    private final AccountService accountService;

    @GetMapping("/account")
    public String listAccont(Model model) {
        model.addAttribute("accountAdmin", accountService.findAllByRole("ADMIN"));
        model.addAttribute("accountEmployee", accountService.findAllByRole("EMPLOYEE"));
        model.addAttribute("accountCustomer", accountService.findAllByRole("CUSTOMER"));
        return "admin/account/list";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Account account = securityUtils.getPrincipal();
        model.addAttribute("account", account);
        return "admin/account/profile";
    }
}
