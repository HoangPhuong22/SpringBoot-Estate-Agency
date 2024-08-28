package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.service.DashboardService;
import zerocoder.com.Estate.utils.SecurityUtils;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Controller(value = "HomeControllerAdmin")
public class HomeController {

    private final DashboardService dashboardService;

    @GetMapping
    public String index(Model theModel) {
        theModel.addAttribute("dashboard", dashboardService.getDashboard());
        return "admin/home";
    }
}
