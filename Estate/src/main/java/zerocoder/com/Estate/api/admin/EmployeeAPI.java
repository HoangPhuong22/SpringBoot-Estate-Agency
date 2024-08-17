package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerocoder.com.Estate.dto.request.EmployeeRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.EmployeeService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "EmployeeAPIAdmin")
@RequestMapping("/api/employee")
public class EmployeeAPI {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseData<?> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        employeeService.save(request);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Employee added successfully", 1);
    }

    @PostMapping("/edit")
    public ResponseData<?> editEmployee(@Valid @RequestBody EmployeeRequest request) {
        employeeService.update(request);
        return new ResponseData<>(HttpStatus.OK.value(), "Employee updated successfully", 1);
    }
}
