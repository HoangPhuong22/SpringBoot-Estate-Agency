package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.AmenityResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.AmenitySearchDTO;
import zerocoder.com.Estate.service.AmenityService;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller(value = "AmenityControllerAdmin")
@RequestMapping(value = "/admin/amenity")
public class AmenityController {

    private final AmenityService amenityService;

    @GetMapping
    public String amenity(@RequestParam Map<String, String> params, Model model) {
        AmenitySearchDTO searchDTO = SearchUtils.amenitySearchDTO(params);
        PageResponse<?> pageResponse = amenityService.findAmenitiesAndSearch(searchDTO);
        List<AmenityResponse> amenities = (List<AmenityResponse>) pageResponse.getContent();
        model.addAttribute("amenities", amenities);
        model.addAttribute("pageRange", PageUtils.getPageNumbers(pageResponse.getTotalPages(), 5, pageResponse.getPageNo()));
        model.addAttribute("pageResponse", pageResponse);
        return "admin/amenity/list";
    }

    @GetMapping("/add")
    public String addAmenity() {
        return "admin/amenity/add";
    }

    @GetMapping("/edit/{id}")
    public String editAmenity(@PathVariable Integer id,  Model model) {
        model.addAttribute("amenity", amenityService.findAmenityById(id));
        return "admin/amenity/edit";
    }
}
