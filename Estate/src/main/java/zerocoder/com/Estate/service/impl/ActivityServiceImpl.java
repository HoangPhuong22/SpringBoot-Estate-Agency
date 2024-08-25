package zerocoder.com.Estate.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zerocoder.com.Estate.dto.request.ActivityRequest;
import zerocoder.com.Estate.dto.response.ActivityResponse;
import zerocoder.com.Estate.enums.ActivityType;
import zerocoder.com.Estate.mapper.ActivityMapper;
import zerocoder.com.Estate.model.Activity;
import zerocoder.com.Estate.model.Customer;
import zerocoder.com.Estate.model.Employee;
import zerocoder.com.Estate.repository.ActivityRepository;
import zerocoder.com.Estate.repository.CustomerRepository;
import zerocoder.com.Estate.repository.EmployeeRepository;
import zerocoder.com.Estate.service.ActivityService;
import zerocoder.com.Estate.utils.SecurityUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final CustomerRepository customerRepository;
    private final ActivityRepository activityRepository;
    private final EmployeeRepository employeeRepository;
    private final SecurityUtils securityUtils;
    private final ActivityMapper activityMapper;


    @Override
    public Long saveOrUpdate(ActivityRequest activityRequest) {
        if(activityRequest.getId() == null)
            return save(activityRequest);
        else
            return update(activityRequest);
    }

    private Long save(ActivityRequest activityRequest) {
        Customer customer = customerRepository.findById(activityRequest.getCustomerId()).orElseThrow();
        Long id = securityUtils.getPrincipal().getEmployee().getId();
        Employee employee = employeeRepository.findById(id).orElseThrow();
        Activity activity = activityMapper.toActivity(activityRequest);
        activity.setCustomer(customer);
        activity.setEmployee(employee);
        return activityRepository.save(activity).getId();
    }

    public Long update(ActivityRequest activityRequest) {
        Activity activity = activityRepository.findById(activityRequest.getId()).orElseThrow();
        activityMapper.updateActivity(activity, activityRequest);
        return activityRepository.save(activity).getId();
    }

    @Override
    public Long delete(Long id) {
        activityRepository.deleteById(id);
        return id;
    }

    @Override
    public List<ActivityResponse> getByTypeAndCustomer(ActivityType type, Long customerId) {
        List<Activity> activities = activityRepository.findByActivityTypeAndCustomerId(type, customerId);
        return activities.stream().map(activityMapper::toActivityResponse).toList();
    }

    @Override
    public List<ActivityResponse> getAll() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(activityMapper::toActivityResponse).toList();
    }
}
