package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.*;
import zerocoder.com.Estate.dto.search.PropertySearchDTO;
import zerocoder.com.Estate.model.Maintenance;
import zerocoder.com.Estate.service.*;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/property")
@Controller(value = "PropertyControllerAdmin")
public class PropertyController {

    private final PropertyService propertyService;
    private final AmenityService amenityService;
    private final InspectionService inspectionService;
    private final CustomerService customerService;
    private final ContractService contractService;
    private final SecurityUtils securityUtils;
    private final MaintenanceService maintenanceService;

    @GetMapping
    public String index(@RequestParam Map<String,String> params, Model model) {
        PropertySearchDTO searchDTO = SearchUtils.propertySearchDTO(params);
        String role = securityUtils.getRole();
        if(role.equals("CUSTOMER")) {
            Long customerId = securityUtils.getPrincipal().getCustomer().getId();
            searchDTO.setCustomerId(customerId);
        }
        if(role.equals("EMPLOYEE")) {
            searchDTO.setIsDeleted(0);
        }
        PageResponse<?> pageResponse = propertyService.findPropertiesAndSearch(searchDTO);
        List<PropertyResponse> properties = (List<PropertyResponse>) pageResponse.getContent();
        List<Integer> pageRange = PageUtils.getPageNumbers(pageResponse.getTotalPages(), 5, pageResponse.getPageNo());
        model.addAttribute("properties", properties);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("pageResponse", pageResponse);
        return "admin/property/list";
    }

    @GetMapping("/add")
    public String addProperty(Model model) {
        model.addAttribute("amenities", amenityService.findAllAmenities());
        return "admin/property/add";
    }

    @GetMapping("/edit/{id}")
    public String editProperty(@PathVariable Long id, Model model) {
        model.addAttribute("property", propertyService.findById(id));
        model.addAttribute("amenities", amenityService.findAllAmenities());
        return "admin/property/edit";
    }

    @GetMapping("/view/{id}")
    public String viewProperty(@PathVariable Long id, Model model) {
        PropertyResponse properties = propertyService.findById(id);
        List<AmenityResponse> amenities = amenityService.findAmenitiesByIds(properties.getAmenityIds());
        List<InspectionResponse> inspections = inspectionService.findInspectionsByPropertyId(id);
        List<ContractResponse> contracts = contractService.findAllContractByPropertyId(id);
        List<CustomerResponse> customers = customerService.getAllCustomers();
        List<MaintenanceResponse> maintenances = maintenanceService.findAllByPropertyId(id);
        model.addAttribute("property", properties);
        model.addAttribute("amenities", amenities);
        model.addAttribute("inspections", inspections);
        model.addAttribute("customers", customers);
        model.addAttribute("contracts", contracts);
        model.addAttribute("maintenances", maintenances);
        return "admin/property/view";
    }
}
