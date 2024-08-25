package zerocoder.com.Estate.api.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zerocoder.com.Estate.dto.request.ActivityRequest;
import zerocoder.com.Estate.dto.response.ResponseData;
import zerocoder.com.Estate.service.ActivityService;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController(value = "ActivityAPIAdmin")
@RequestMapping("/api/activity")
public class ActivityAPI {

    private final ActivityService activityService;

    @PostMapping("/save")
    public ResponseData<?> addActivity(@Valid @RequestBody ActivityRequest activityRequest) {
        Long id = activityService.saveOrUpdate(activityRequest);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Add activity successfully", id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseData<?> deleteActivity(@PathVariable Long id) {
        Long activityId = activityService.delete(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Delete activity successfully", activityId);
    }
}
