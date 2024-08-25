package zerocoder.com.Estate.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zerocoder.com.Estate.dto.request.ActivityRequest;
import zerocoder.com.Estate.dto.response.ActivityResponse;
import zerocoder.com.Estate.enums.ActivityType;
import zerocoder.com.Estate.model.Activity;
import zerocoder.com.Estate.service.AccountService;

@Component
@RequiredArgsConstructor
public class ActivityMapper {

    private final AccountService accountService;

    public Activity toActivity(ActivityRequest activityRequest) {
        return Activity.builder()
                .activityType(ActivityType.valueOf(activityRequest.getType()))
                .activityTime(activityRequest.getTime())
                .location(activityRequest.getLocation())
                .result(activityRequest.getResult())
                .note(activityRequest.getNote())
                .cost(activityRequest.getCost())
                .build();
    }

    public ActivityResponse toActivityResponse(Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .location(activity.getLocation())
                .note(activity.getNote())
                .cost(activity.getCost())
                .result(activity.getResult())
                .type(activity.getActivityType())
                .time(activity.getActivityTime())
                .createdBy(accountService.getUserName(activity.getCreatedBy()))
                .updatedBy(accountService.getUserName(activity.getUpdatedBy()))
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }
    public void updateActivity(Activity activity, ActivityRequest activityRequest) {
        activity.setActivityType(ActivityType.valueOf(activityRequest.getType()));
        activity.setActivityTime(activityRequest.getTime());
        activity.setLocation(activityRequest.getLocation());
        activity.setResult(activityRequest.getResult());
        activity.setCost(activityRequest.getCost());
        activity.setNote(activityRequest.getNote());
    }
}
