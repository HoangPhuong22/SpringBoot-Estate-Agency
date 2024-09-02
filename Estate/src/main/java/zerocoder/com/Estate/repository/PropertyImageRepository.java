package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerocoder.com.Estate.model.PropertyImage;

@Repository
public interface PropertyImageRepository extends JpaRepository<PropertyImage, Long> {
    void deleteAllByPropertyId(Long propertyId);
}
