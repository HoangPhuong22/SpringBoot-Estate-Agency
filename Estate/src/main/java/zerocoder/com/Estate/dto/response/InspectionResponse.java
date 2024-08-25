package zerocoder.com.Estate.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.InspectionStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class InspectionResponse extends AbstractResponse {
    private Long id;
    private String type;
    private LocalDateTime inspectionDate;
    private String report;
    private InspectionStatus status;
}
