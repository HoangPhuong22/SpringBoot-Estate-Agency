package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.exception.UniqueException;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.repository.CustomerRepository;
import zerocoder.com.Estate.repository.EmployeeRepository;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.service.CustomerService;
import zerocoder.com.Estate.service.EmployeeService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Long saveAccount(AccountRequest request) {
        if(accountRepository.existsByEmail(request.getEmail())) {
            throw new UniqueException("Email đã tồn tại", "email");
        }
        if(accountRepository.existsByUsername(request.getUsername())) {
            throw new UniqueException("Tên đăng nhập đã tồn tại", "username");
        }
        Object user;
        if(request.getType() == 1) {
            Employee userTemp = employeeRepository.findById(request.getId()).orElseThrow();
            if(userTemp.getAccount() != null) {
                throw new UniqueException("Nhân viên đã có tài khoản", "employee");
            }
            user = userTemp;
        } else {
            Customer userTemp = customerRepository.findById(request.getId()).orElseThrow();
            if(userTemp.getAccount() != null) {
                throw new UniqueException("Khách hàng đã có tài khoản", "customer");
            }
            user = userTemp;
        }
        Account account = Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if(request.getType() == 1) {
            account.addEmployee((Employee) user);
        } else {
            account.addCustomer((Customer) user);
        }
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public String getUserName(Long id) {
        if(id == null) return "Anonymous";
        Account account = accountRepository.findById(id).orElse(null);
        return account != null ? account.getUsername() : "Anonymous";
    }

    @Override
    public void changePassword(Long id, String password) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setPassword(passwordEncoder.encode(password));
        accountRepository.save(account);
    }
}
