package zerocoder.com.Estate.mapper;

import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.CustomerRequest;
import zerocoder.com.Estate.dto.response.CustomerResponse;
import zerocoder.com.Estate.enums.CustomerStatus;
import zerocoder.com.Estate.enums.CustomerType;
import zerocoder.com.Estate.enums.Gender;
import zerocoder.com.Estate.model.Customer;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        String code = "KH" + System.currentTimeMillis();
        return Customer.builder()
                .code(code)
                .fullName(customerRequest.getFullName())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .address(customerRequest.getAddress())
                .type(CustomerType.valueOf(customerRequest.getType()))
                .birthDay(customerRequest.getBirthDay())
                .idNumber(customerRequest.getIdNumber())
                .gender(Gender.valueOf(customerRequest.getGender()))
                .status(CustomerStatus.valueOf(customerRequest.getStatus()))
                .build();
    }
    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .idNumber(customer.getIdNumber())
                .address(customer.getAddress())
                .status(customer.getStatus())
                .type(customer.getType())
                .birthDay(customer.getBirthDay())
                .gender(customer.getGender())
                .createdBy(customer.getCreatedBy())
                .updatedBy(customer.getUpdatedBy())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .accountId((customer.getAccount() != null) ? customer.getAccount().getId() : null)
                .userName((customer.getAccount() != null) ? customer.getAccount().getUsername() : null)
                .build();
    }
    public void toCustomer(Customer customer, CustomerRequest customerRequest) {
        customer.setFullName(customerRequest.getFullName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        customer.setAddress(customerRequest.getAddress());
        customer.setType(CustomerType.valueOf(customerRequest.getType()));
        customer.setBirthDay(customerRequest.getBirthDay());
        customer.setIdNumber(customerRequest.getIdNumber());
        customer.setGender(Gender.valueOf(customerRequest.getGender()));
        customer.setStatus(CustomerStatus.valueOf(customerRequest.getStatus()));
    }

}
