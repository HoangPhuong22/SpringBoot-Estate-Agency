package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.EmployeeRequest;
import zerocoder.com.Estate.dto.response.EmployeeResponse;
import zerocoder.com.Estate.dto.response.PageResponse;
import zerocoder.com.Estate.dto.search.EmployeeSearchDTO;
import zerocoder.com.Estate.exception.UniqueException;
import zerocoder.com.Estate.mapper.EmployeeMapper;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.EmployeeRepository;
import zerocoder.com.Estate.service.EmployeeService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Long save(EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        if(employeeRepository.existsByEmail(employee.getEmail())) {
            log.error("Email already exists");
            throw new UniqueException("Email đã tồn tại", "email");
        }
        if(employeeRepository.existsByPhone(employee.getPhone())) {
            log.error("Phone already exists");
            throw new UniqueException("Số điện thoại đã tồn tại", "phone");
        }
        if(employeeRepository.existsByIdNumber(employee.getIdNumber())) {
            log.error("Id number already exists");
            throw new UniqueException("Số CCCD đã tồn tại", "idNumber");
        }
        employeeRepository.save(employee);
        return employee.getId();
    }

    @Override
    public Long update(EmployeeRequest request) {
        Employee employee = employeeRepository.findById(request.getId()).orElseThrow();
        employeeMapper.updateEmployee(employee, request);
        if(employeeRepository.existsByEmailAndIdNot(employee.getEmail(), employee.getId())) {
            log.error("Email already exists");
            throw new UniqueException("Email đã tôn tại", "email");
        }
        if(employeeRepository.existsByPhoneAndIdNot(employee.getPhone(), employee.getId())) {
            log.error("Phone already exists");
            throw new UniqueException("Số điện thoại đã tồn tại", "phone");
        }
        if(employeeRepository.existsByIdNumberAndIdNot(employee.getIdNumber(), employee.getId())) {
            log.error("Id number already exists");
            throw new UniqueException("Số CCCD đã tồn tại", "idNumber");
        }
        employeeRepository.save(employee);
        return employee.getId();
    }

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public PageResponse<?> search(EmployeeSearchDTO searchDTO) {
        PageResponse<?> pageResponse = employeeRepository.search(searchDTO);
        List<Employee> employees = (List<Employee>) pageResponse.getContent();
        List<EmployeeResponse> employeeResponses = employees.stream().map(employeeMapper::toEmployeeResponse).toList();

        return PageResponse.builder()
                .content(employeeResponses)
                .pageNo(pageResponse.getPageNo())
                .pageSize(pageResponse.getPageSize())
                .totalPages(pageResponse.getTotalPages())
                .totalElements(pageResponse.getTotalElements())
                .build();
    }

    @Override
    public List<EmployeeResponse> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employeeMapper::toEmployeeResponse).toList();
    }
}
