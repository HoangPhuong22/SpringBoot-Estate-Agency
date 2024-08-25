package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Maintenance;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findAllByPropertyIdAndAccountId(Long propertyId, Long accountId);
    List<Maintenance> findAllByPropertyId(Long propertyId);
}
