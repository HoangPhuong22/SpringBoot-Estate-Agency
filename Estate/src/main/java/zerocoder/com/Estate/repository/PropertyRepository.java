package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.enums.PropertyStatus;
import zerocoder.com.Estate.model.Property;
import zerocoder.com.Estate.repository.criteria.PropertyCriteriaRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>, PropertyCriteriaRepository {

    Long countPropertyByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    List<Property> findTop5ByOrderByCreatedAtDesc();
    List<Property> findTop5ByIsDeletedOrderByCreatedAtDesc(Boolean isDeleted);
    List<Property> findTop3ByIsDeletedOrderByCreatedAtDesc(Boolean isDeleted);
}
