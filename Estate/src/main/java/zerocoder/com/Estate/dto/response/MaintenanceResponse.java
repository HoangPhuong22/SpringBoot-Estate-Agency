package zerocoder.com.Estate.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.MaintenanceStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class MaintenanceResponse extends AbstractResponse implements Serializable {
    private Long id;
    private String description;
    private MaintenanceStatus status;
    private Long estimatedCost;
    private Long actualCost;
    private LocalDateTime completedAt;
}
