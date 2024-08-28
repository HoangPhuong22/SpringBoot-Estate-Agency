package zerocoder.com.Estate.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ActivityContractResponse {
    private String employeeName;
    private String customerName;
    private String propertyName;
    private Long propertyId;
    private String codeContract;
    private String timeContract;
    private Integer type;
}
