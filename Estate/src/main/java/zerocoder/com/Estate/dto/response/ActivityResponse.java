package zerocoder.com.Estate.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.ActivityType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class ActivityResponse  extends AbstractResponse implements Serializable {
    private Long id;
    private String location;
    private String note;
    private Long cost;
    private String result;
    private ActivityType type;
    private LocalDateTime time;
}
