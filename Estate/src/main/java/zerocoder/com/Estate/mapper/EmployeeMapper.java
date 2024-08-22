package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.EmployeeRequest;
import zerocoder.com.Estate.dto.response.EmployeeResponse;
import zerocoder.com.Estate.enums.Gender;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.service.AccountService;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {

    private final AccountService accountService;

    public Employee toEmployee(EmployeeRequest request) {
        String code = "NV" + System.currentTimeMillis();
        return Employee.builder()
                .code(code)
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .birthDay(request.getBirthDay())
                .idNumber(request.getIdNumber())
                .gender(Gender.valueOf(request.getGender()))
                .education(request.getEducation())
                .hireDate(request.getHireDate())
                .build();

    }
    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .code(employee.getCode())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .gender(employee.getGender())
                .idNumber(employee.getIdNumber())
                .address(employee.getAddress())
                .birthDay(employee.getBirthDay())
                .education(employee.getEducation())
                .hireDate(employee.getHireDate())
                .isActive(employee.getIsActive())
                .createdBy(accountService.getUserName(employee.getCreatedBy()))
                .updatedBy(accountService.getUserName(employee.getUpdatedBy()))
                .updatedAt(employee.getUpdatedAt())
                .createdAt(employee.getCreatedAt())
                .accountId(employee.getAccount() != null ? employee.getAccount().getId() : null)
                .userName(employee.getAccount() != null ? employee.getAccount().getUsername() : null)
                .build();
    }
    public void updateEmployee(Employee employee, EmployeeRequest request) {
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setAddress(request.getAddress());
        employee.setBirthDay(request.getBirthDay());
        employee.setIdNumber(request.getIdNumber());
        employee.setGender(Gender.valueOf(request.getGender()));
        employee.setEducation(request.getEducation());
        employee.setHireDate(request.getHireDate());
        employee.setIsActive(request.getIsActive());
    }
}
