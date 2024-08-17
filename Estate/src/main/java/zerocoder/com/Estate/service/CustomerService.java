package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.CustomerRequest;
import zerocoder.com.Estate.dto.response.CustomerResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;

import java.util.List;

public interface CustomerService {
    Long addCustomer(CustomerRequest customerRequest);
    Long updateCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomerById(Long id);
    List<CustomerResponse> getAllCustomers();
    PageResponse<?> search(CustomerSearchDTO customerSearchDTO);
}
