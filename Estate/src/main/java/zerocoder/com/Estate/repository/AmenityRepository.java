package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Amenity;
import zerocoder.com.Estate.repository.criteria.AmenityCriteriaRepository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer>, AmenityCriteriaRepository {
}
