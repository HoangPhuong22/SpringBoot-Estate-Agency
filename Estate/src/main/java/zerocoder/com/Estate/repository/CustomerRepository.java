package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.criteria.CustomerCriteriaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerCriteriaRepository {

    Boolean existsByIdNumber(String idNumber);
    Boolean existsByIdNumberAndIdNot(String idNumber, Long id);

    Long countCustomerByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Customer> findTop5ByOrderByCreatedAtDesc();
}
