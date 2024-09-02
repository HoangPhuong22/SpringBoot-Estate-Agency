package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zerocoder.com.Estate.dto.request.AccountEditRequest;
import zerocoder.com.Estate.dto.request.AccountRequest;
import zerocoder.com.Estate.dto.response.AccountResponse;
import zerocoder.com.Estate.exception.EmptyFileException;
import zerocoder.com.Estate.exception.UniqueException;
import zerocoder.com.Estate.mapper.AccountMapper;
import zerocoder.com.Estate.model.Account;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.model.Role;
import zerocoder.com.Estate.repository.AccountRepository;
import zerocoder.com.Estate.repository.CustomerRepository;
import zerocoder.com.Estate.repository.EmployeeRepository;
import zerocoder.com.Estate.repository.RoleRepository;
import zerocoder.com.Estate.service.AccountService;
import zerocoder.com.Estate.utils.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AccountMapper accountMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public Long saveAccount(AccountRequest request, MultipartFile avatar) {
        if(avatar == null) {
            throw new EmptyFileException("Ảnh đại diện không được để trống", "avatar");
        }
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
        String fileName = FileUtils.save(uploadDir, avatar);
        Account account = Account.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .avatarUrl(fileName)
                .build();

        if(request.getType() == 1) {
            account.addEmployee((Employee) user);
            Role role = roleRepository.findByName("EMPLOYEE").orElseThrow();
            account.setRoles(Set.of(role));
        } else {
            account.addCustomer((Customer) user);
            Role role = roleRepository.findByName("CUSTOMER").orElseThrow();
            account.setRoles(Set.of(role));
        }
        accountRepository.save(account);
        return account.getId();
    }

    @Override
    public Long editAccount(AccountEditRequest request, MultipartFile avatar) {
        Account account = accountRepository.findById(request.getId()).orElseThrow();
        if(accountRepository.existsByEmailAndIdNot(request.getEmail(), request.getId())) {
            throw new UniqueException("Email đã tồn tại", "email");
        }
        if(accountRepository.existsByUsernameAndIdNot(request.getUsername(), request.getId())) {
            throw new UniqueException("Tên đăng nhập đã tồn tại", "username");
        }
        if(avatar != null) {
            String fileName = FileUtils.save(uploadDir, avatar);
            FileUtils.delete(uploadDir, account.getAvatarUrl());
            account.setAvatarUrl(fileName);
        }
        account.setEmail(request.getEmail());
        account.setUsername(request.getUsername());
        if(request.getRole() != null && !request.getRole().isEmpty()) {
            Role role = roleRepository.findByName(request.getRole()).orElseThrow();
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            account.setRoles(roles);
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

    @Override
    public void editDelete(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setIsDeleted(!account.getIsDeleted());
        accountRepository.save(account);
    }

    @Override
    public List<AccountResponse> findAllByRole(String role) {
        List<Account> accounts = accountRepository.findAllByRoleName(role);
        return accounts.stream().map(accountMapper::toAccountResponse).toList();
    }
}
