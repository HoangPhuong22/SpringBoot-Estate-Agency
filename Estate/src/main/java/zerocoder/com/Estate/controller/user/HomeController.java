package zerocoder.com.Estate.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.AmenityResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.service.AmenityService;
import zerocoder.com.Estate.service.PropertyService;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping(value = "/")
@Controller(value = "UserHomeController")
public class HomeController {

    private final PropertyService propertyService;
    private final AmenityService amenityService;

    @GetMapping
    public String home(Model model) {
        List<PropertyResponse> properties = propertyService.findTop5Properties();
        List<PropertyResponse> top3Properties = propertyService.findTop3Properties();
        model.addAttribute("properties", properties);
        model.addAttribute("top3Properties", top3Properties);
        return "user/home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        return "user/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "user/contact";
    }

    @GetMapping("/property")
    public String property(@RequestParam Map<String,String> params, Model model) {
        PropertySearchDTO searchDTO = SearchUtils.propertySearchDTO(params);
        searchDTO.setIsDeleted(0);
        PageResponse<?> pageResponse = propertyService.findPropertiesAndSearch(searchDTO);
        List<PropertyResponse> properties = (List<PropertyResponse>) pageResponse.getContent();
        List<Integer> pageRange = PageUtils.getPageNumbers(pageResponse.getTotalPages(), 5, pageResponse.getPageNo());
        model.addAttribute("properties", properties);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("pageResponse", pageResponse);
        return "user/property";
    }

    @GetMapping("/property/{id}")
    public String propertyDetail(@PathVariable Long id, Model model) {
        PropertyResponse property = propertyService.findById(id);
        List<AmenityResponse> amenities = amenityService.findAmenitiesByIds(property.getAmenityIds());
        model.addAttribute("amenities", amenities);
        model.addAttribute("property", property);
        return "user/property-detail";
    }
}
