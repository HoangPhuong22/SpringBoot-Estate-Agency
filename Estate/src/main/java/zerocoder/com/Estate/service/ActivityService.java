package zerocoder.com.Estate.service;

import zerocoder.com.Estate.dto.request.ActivityRequest;
import zerocoder.com.Estate.dto.response.ActivityResponse;
import zerocoder.com.Estate.enums.ActivityType;

import java.util.List;

public interface ActivityService {
    Long saveOrUpdate(ActivityRequest activityRequest);
    Long delete(Long id);

    List<ActivityResponse> getByTypeAndCustomer(ActivityType type, Long customerId);
    List<ActivityResponse> getAll();
}
