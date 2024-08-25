package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerocoder.com.Estate.model.Inspection;

import java.util.List;

public interface InspectionRepository extends JpaRepository<Inspection, Long> {
    List<Inspection> findByPropertyId(Long propertyId);
}
