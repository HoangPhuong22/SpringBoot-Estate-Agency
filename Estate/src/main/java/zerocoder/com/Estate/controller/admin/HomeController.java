package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Controller(value = "AdminHomeController")
public class HomeController {
    @GetMapping
    public String index() {
        return "admin/home";
    }
}
