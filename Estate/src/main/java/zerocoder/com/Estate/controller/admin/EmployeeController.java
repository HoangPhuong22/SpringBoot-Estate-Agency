package zerocoder.com.Estate.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zerocoder.com.Estate.dto.response.EmployeeResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.EmployeeSearchDTO;
import zerocoder.com.Estate.service.EmployeeService;
import zerocoder.com.Estate.utils.PageUtils;
import zerocoder.com.Estate.utils.SearchUtils;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller(value = "EmployeeControllerAdmin")
@RequestMapping(value = "/admin/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @GetMapping
    public String employee(@RequestParam Map<String,String> params, Model model) {
        EmployeeSearchDTO searchDTO = SearchUtils.employeeSearchDTO(params);
        PageResponse<?> pageResponse = employeeService.search(searchDTO);
        List<EmployeeResponse> employees = (List<EmployeeResponse>) pageResponse.getContent();
        model.addAttribute("employees", employees);
        model.addAttribute("pageRange", PageUtils.getPageNumbers(pageResponse.getTotalPages() ,5, pageResponse.getPageNo()));
        model.addAttribute("pageResponse", pageResponse);
        return "admin/employee/list";
    }

    @GetMapping("/add")
    public String addEmployee() {
        return "admin/employee/add";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getById(id));
        return "admin/employee/edit";
    }
}
