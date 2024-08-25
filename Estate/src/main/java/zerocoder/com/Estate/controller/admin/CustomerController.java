package zerocoder.com.Estate.controller.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.CustomerResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;
import zerocoder.com.Estate.enums.ActivityType;
import zerocoder.com.Estate.service.ActivityService;
import zerocoder.com.Estate.service.CustomerService;
import zerocoder.com.Estate.service.EmployeeService;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller(value = "CustomerControllerAdmin")
@RequestMapping(value = "/admin/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final ActivityService activityService;
    private final SecurityUtils securityUtils;

    @GetMapping
    public String customer(@RequestParam Map<String,String> params, Model model) {
        CustomerSearchDTO customerSearchDTO = SearchUtils.customerSearchDTO(params);
        if(securityUtils.getRole().equals("EMPLOYEE")) {
            customerSearchDTO.setUserNameEmployee(securityUtils.getPrincipal().getUsername());
        }
        PageResponse<?> pageResponse = customerService.search(customerSearchDTO);
        List<CustomerResponse> customers = (List<CustomerResponse>) pageResponse.getContent();
        model.addAttribute("customers", customers);
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("pageRange", PageUtils.getPageNumbers(pageResponse.getTotalPages(), 5, pageResponse.getPageNo()));
        model.addAttribute("employees", employeeService.getAll());
        return "admin/customer/list";
    }

    @GetMapping("/add")
    public String addCustomer() {
        return "admin/customer/add";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        model.addAttribute("typeCode", ActivityType.type());
        model.addAttribute("mettingType", activityService.getByTypeAndCustomer(ActivityType.MEETING, id));
        model.addAttribute("contractType", activityService.getByTypeAndCustomer(ActivityType.CONTRACT, id));
        model.addAttribute("otherType", activityService.getByTypeAndCustomer(ActivityType.OTHER, id));
        return "admin/customer/edit";
    }
}
