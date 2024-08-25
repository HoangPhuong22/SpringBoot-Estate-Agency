package zerocoder.com.Estate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerocoder.com.Estate.enums.ActivityType;
import zerocoder.com.Estate.model.Activity;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByActivityTypeAndCustomerId(ActivityType activityType, Long customerId);
}
