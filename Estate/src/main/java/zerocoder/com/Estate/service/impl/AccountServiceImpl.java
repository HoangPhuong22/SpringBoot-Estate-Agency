package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.exception.UniqueException;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.repository.EmployeeRepository;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.service.EmployeeService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public Long saveAccount(AccountRequest request) {
        if(accountRepository.existsByEmail(request.getEmail())) {
            throw new UniqueException("Email đã tồn tại", "email");
        }
        if(accountRepository.existsByUsername(request.getUsername())) {
            throw new UniqueException("Tên đăng nhập đã tồn tại", "username");
        }
        Employee employee = employeeRepository.findById(request.getEmployeeId()).orElseThrow();
        if(employee.getAccount() != null) {
            throw new UniqueException("Nhân viên đã có tài khoản", "employee");
        }
        Account account = Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
        account.addEmployee(employee);
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public void changePassword(Long id, String password) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setPassword(password);
        accountRepository.save(account);
    }
}
