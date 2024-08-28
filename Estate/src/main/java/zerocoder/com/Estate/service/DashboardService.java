package zerocoder.com.Estate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.response.ActivityContractResponse;
import zerocoder.com.Estate.dto.response.CustomerResponse;
import zerocoder.com.Estate.dto.response.DashboardResponse;
import zerocoder.com.Estate.dto.response.PropertyResponse;
import zerocoder.com.Estate.mapper.CustomerMapper;
import zerocoder.com.Estate.mapper.PropertyMapper;
import zerocoder.com.Estate.repository.*;
import zerocoder.com.Estate.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final PropertyRepository propertyRepository;
    private final ContractRepository contractRepository;
    private final PropertyMapper propertyMapper;
    private final CustomerMapper customerMapper;


    public DashboardResponse getDashboard() {
        return DashboardResponse.builder()
                .countCustomer(countCustomer())
                .countEmployee(countEmployee())
                .countProperty(countProperty())
                .customerGrowthRate(getCustomerGrowthRate())
                .employeeGrowthRate(getEmployeeGrowthRate())
                .propertyGrowthRate(getPropertyGrowthRate())
                .top5Activity(getTop5Activity())
                .top5Property(getTop5Property())
                .top5Customer(getTop5Customer())
                .build();
    }

    public List<PropertyResponse> getTop5Property() {
        return propertyRepository.findTop5ByOrderByCreatedAtDesc().stream()
                .map(propertyMapper::toPropertyResponse)
                .toList();
    }

    public List<CustomerResponse> getTop5Customer() {
        return customerRepository.findTop5ByOrderByCreatedAtDesc().stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public List<ActivityContractResponse> getTop5Activity() {
        return contractRepository.findTop5ByOrderByUpdatedAtDesc().stream()
                .map(contract -> ActivityContractResponse.builder()
                        .employeeName(TimeUtils.isEqual(contract.getCreatedAt(), contract.getUpdatedAt()) ?
                                accountRepository.findById(contract.getCreatedBy()).get().getUsername() :
                                accountRepository.findById(contract.getUpdatedBy()).get().getUsername())
                        .customerName(contract.getCustomer().getFullName())
                        .propertyName(contract.getProperty().getName())
                        .propertyId(contract.getProperty().getId())
                        .codeContract(contract.getCode())
                        .timeContract(TimeUtils.isEqual(contract.getCreatedAt(), contract.getUpdatedAt())
                                ? TimeUtils.convertTime(contract.getCreatedAt()) : TimeUtils.convertTime(contract.getUpdatedAt()))
                        .type(TimeUtils.isEqual(contract.getCreatedAt(), contract.getUpdatedAt()) ? 1 : 2)
                        .build())
                .toList();
    }
    public Long countCustomer() {
        return customerRepository.count();
    }

    public Long countEmployee() {
        return employeeRepository.count();
    }

    public Long countProperty() {
        return propertyRepository.count();
    }

    public double getCustomerGrowthRate() {
        long lastMonthCustomers = customerRepository.countCustomerByCreatedAtBetween(
                LocalDateTime.now().minusMonths(2).withDayOfMonth(1),
                LocalDateTime.now().minusMonths(1).withDayOfMonth(1)
        );
        long thisMonthCustomers = customerRepository.countCustomerByCreatedAtBetween(
                LocalDateTime.now().minusMonths(1).withDayOfMonth(1),
                LocalDateTime.now().withDayOfMonth(1)
        );

        if (lastMonthCustomers == 0) {
            return 100;
        }

        return ((double) (thisMonthCustomers - lastMonthCustomers) / lastMonthCustomers) * 100;
    }

    public double getEmployeeGrowthRate() {
        long lastMonthEmployees = employeeRepository.countEmployeeByCreatedAtBetween(
                LocalDateTime.now().minusMonths(2).withDayOfMonth(1),
                LocalDateTime.now().minusMonths(1).withDayOfMonth(1)
        );
        long thisMonthEmployees = employeeRepository.countEmployeeByCreatedAtBetween(
                LocalDateTime.now().minusMonths(1).withDayOfMonth(1),
                LocalDateTime.now().withDayOfMonth(1)
        );

        if (lastMonthEmployees == 0) {
            return 100;
        }

        return ((double) (thisMonthEmployees - lastMonthEmployees) / lastMonthEmployees) * 100;
    }

    public double getPropertyGrowthRate() {
        long lastMonthProperties = propertyRepository.countPropertyByCreatedAtBetween(
                LocalDateTime.now().minusMonths(2).withDayOfMonth(1),
                LocalDateTime.now().minusMonths(1).withDayOfMonth(1)
        );
        long thisMonthProperties = propertyRepository.countPropertyByCreatedAtBetween(
                LocalDateTime.now().minusMonths(1).withDayOfMonth(1),
                LocalDateTime.now().withDayOfMonth(1)
        );

        if (lastMonthProperties == 0) {
            return 100;
        }

        return ((double) (thisMonthProperties - lastMonthProperties) / lastMonthProperties) * 100;
    }
}
