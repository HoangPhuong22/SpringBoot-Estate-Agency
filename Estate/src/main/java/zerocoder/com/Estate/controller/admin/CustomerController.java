package zerocoder.com.Estate.controller.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.CustomerResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;
import zerocoder.com.Estate.service.CustomerService;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller(value = "CustomerControllerAdmin")
@RequestMapping(value = "/admin/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public String customer(@RequestParam Map<String,String> params, Model model) {
        CustomerSearchDTO customerSearchDTO = SearchUtils.customerSearchDTO(params);
        PageResponse<?> pageResponse = customerService.search(customerSearchDTO);
        List<CustomerResponse> customers = (List<CustomerResponse>) pageResponse.getContent();
        model.addAttribute("customers", customers);
        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("pageRange", PageUtils.getPageNumbers(pageResponse.getTotalPages(), 5, pageResponse.getPageNo()));
        return "admin/customer/list";
    }

    @GetMapping("/add")
    public String addCustomer() {
        return "admin/customer/add";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "admin/customer/edit";
    }
}
