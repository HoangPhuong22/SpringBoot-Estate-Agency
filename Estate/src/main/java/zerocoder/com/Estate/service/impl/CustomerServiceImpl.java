package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.CustomerRequest;
import zerocoder.com.Estate.dto.response.CustomerResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.CustomerSearchDTO;
import zerocoder.com.Estate.exception.UniqueException;
import zerocoder.com.Estate.mapper.CustomerMapper;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.repository.CustomerRepository;
import zerocoder.com.Estate.service.CustomerService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Long addCustomer(CustomerRequest customerRequest) {
        if(customerRepository.existsByEmail(customerRequest.getEmail())) {
            throw new UniqueException("Email đã tồn tại", "email");
        }
        if(customerRepository.existsByPhone(customerRequest.getPhone())) {
            throw new UniqueException("Số điện thoại đã tồn tại", "phone");
        }
        if(customerRepository.existsByIdNumber(customerRequest.getIdNumber())) {
            throw new UniqueException("Số chứng minh nhân dân đã tồn tại", "idNumber");
        }
        Customer customer = customerMapper.toCustomer(customerRequest);
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public Long updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
        if(customerRepository.existsByEmailAndIdNot(customerRequest.getEmail(), customerRequest.getId())) {
            throw new UniqueException("Email đã tồn tại", "email");
        }
        if(customerRepository.existsByPhoneAndIdNot(customerRequest.getPhone(), customerRequest.getId())) {
            throw new UniqueException("Số điện thoại đã tồn tại", "phone");
        }
        if(customerRepository.existsByIdNumberAndIdNot(customerRequest.getIdNumber(), customerRequest.getId())) {
            throw new UniqueException("Số chứng minh nhân dân đã tồn tại", "idNumber");
        }
        customerMapper.toCustomer(customer, customerRequest);
        customerRepository.save(customer);
        return customer.getId();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng"));
        return customerMapper.toCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toCustomerResponse).toList();
    }

    @Override
    public PageResponse<?> search(CustomerSearchDTO customerSearchDTO) {
        PageResponse<?> pageResponse = customerRepository.search(customerSearchDTO);
        List<CustomerResponse> customerResponses = ((List<Customer>) pageResponse.getContent()).stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
        return PageResponse.builder()
                .content(customerResponses)
                .pageNo(pageResponse.getPageNo())
                .pageSize(pageResponse.getPageSize())
                .totalPages(pageResponse.getTotalPages())
                .totalElements(pageResponse.getTotalElements())
                .build();
    }
}
