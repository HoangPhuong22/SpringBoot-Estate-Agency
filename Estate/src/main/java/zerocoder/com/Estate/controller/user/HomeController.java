package zerocoder.com.Estate.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerocoder.com.Estate.service.DashboardService;

@RequiredArgsConstructor
@RequestMapping(value = "/")
@Controller(value = "UserHomeController")
public class HomeController {


    @GetMapping
    public String home(Model model) {
        return "user/home";
    }
}
