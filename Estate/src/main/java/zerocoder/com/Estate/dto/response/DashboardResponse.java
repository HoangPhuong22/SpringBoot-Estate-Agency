package zerocoder.com.Estate.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DashboardResponse {
    private long countCustomer;
    private long countEmployee;
    private long countProperty;
    private double customerGrowthRate;
    private double employeeGrowthRate;
    private double propertyGrowthRate;
    private Long revenue;
    private List<ActivityContractResponse> top5Activity;
    private List<PropertyResponse> top5Property;
    private List<CustomerResponse> top5Customer;
}
