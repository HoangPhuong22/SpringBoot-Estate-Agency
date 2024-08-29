package zerocoder.com.Estate.api.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zerocoder.com.Estate.dto.request.CustomerUserRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.CustomerService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "CustomerAPIUser")
@RequestMapping("/user/customer")
public class CustomerUserAPI {

    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseData<?> addCustomer(@Valid @RequestBody CustomerUserRequest customerRequest) {
        Long id = customerService.addCustomer(customerRequest);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Add customer successfully", id);
    }
}
