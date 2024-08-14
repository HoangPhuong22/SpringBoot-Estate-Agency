package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping(value = "/admin")
@Controller(value = "HomeControllerAdmin")
public class HomeController {
    @GetMapping
    public String index(Model theModel) {
        return "admin/home";
    }
}
