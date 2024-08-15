package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.service.PropertyService;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/property")
@Controller(value = "PropertyControllerAdmin")
public class PropertyController {
    private final PropertyService propertyService;

    @GetMapping
    public String index(@RequestParam Map<String,String> params, Model model) {
        PropertySearchDTO searchDTO = SearchUtils.propertySearchDTO(params);
        PageResponse<?> pageResponse = propertyService.findPropertiesAndSearch(searchDTO);
        List<PropertyResponse> properties = (List<PropertyResponse>) pageResponse.getContent();
        List<Integer> pageRange = PageUtils.getPageNumbers(pageResponse.getTotalPages(), 5, pageResponse.getPageNo());
        model.addAttribute("properties", properties);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("pageResponse", pageResponse);
        return "admin/property/list";
    }

    @GetMapping("/add")
    public String addProperty() {
        return "admin/property/add";
    }

    @GetMapping("/edit/{id}")
    public String editProperty(@PathVariable Long id, Model model) {
        model.addAttribute("property", propertyService.findById(id));
        return "admin/property/edit";
    }
}
