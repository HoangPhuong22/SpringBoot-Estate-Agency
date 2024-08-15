package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zerocoder.com.Estate.service.PropertyService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/property")
@Controller(value = "PropertyControllerAdmin")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("properties", propertyService.findAll());
        return "admin/property/list";
    }

    @GetMapping("/add")
    public String addProperty() {
        return "admin/property/add";
    }
}
