package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class AssignmentRequest implements Serializable {
    @NotNull(message = "id khách hàng không được để trống")
    private Long customerId;

    private List<Long> employeeIds;
}
