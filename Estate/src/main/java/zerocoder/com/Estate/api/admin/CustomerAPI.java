package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.AssignmentRequest;
import zerocoder.com.Estate.dto.request.CustomerRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.CustomerService;

import java.util.List;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "CustomerAPIAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseData<?> addCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        Long id = customerService.addCustomer(customerRequest);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Add customer successfully", id);
    }

    @PutMapping("/edit")
    public ResponseData<?> editCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        Long id = customerService.updateCustomer(customerRequest);
        return new ResponseData<>(HttpStatus.OK.value(), "Edit customer successfully", id);
    }

    @PostMapping("/assignment")
    public ResponseData<?> assignEmployee(@RequestBody AssignmentRequest assignmentRequest) {
        return new ResponseData<>(HttpStatus.OK.value(), "Employee assigned successfully", customerService.assignEmployee(assignmentRequest));
    }
}
