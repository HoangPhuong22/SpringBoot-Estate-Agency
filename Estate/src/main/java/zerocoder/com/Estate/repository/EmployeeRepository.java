package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.criteria.EmployeeCriteriaRepository;

import java.time.LocalDateTime;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeCriteriaRepository {
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
    Boolean existsByIdNumber(String idNumber);

    Boolean existsByEmailAndIdNot(String email, Long id);
    Boolean existsByPhoneAndIdNot(String phone, Long id);
    Boolean existsByIdNumberAndIdNot(String idNumber, Long id);

    Long countEmployeeByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
