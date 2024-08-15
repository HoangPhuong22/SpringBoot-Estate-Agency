package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
