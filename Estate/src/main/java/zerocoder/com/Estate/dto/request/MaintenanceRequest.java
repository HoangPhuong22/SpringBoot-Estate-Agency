package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import zerocoder.com.Estate.validator.EnumNamePattern;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequest implements Serializable {
    private Long id;

    @NotBlank(message = "Vui lòng nhập mô tả")
    private String description;

    @NotNull(message = "PropertyId không được để trống")
    private Long propertyId;

    @NotNull(message = "Chi phí ước lượng không được để trống")
    private Long estimatedCost;
    private Long actualCost;
    private String status;
    private LocalDateTime completedAt;
}
