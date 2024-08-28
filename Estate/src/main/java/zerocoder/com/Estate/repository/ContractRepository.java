package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerocoder.com.Estate.model.Contract;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAllByPropertyId(Long propertyId);
    List<Contract> findAllByPropertyIdAndCustomerId(Long propertyId, Long customerId);

    List<Contract> findTop5ByOrderByUpdatedAtDesc();
}
