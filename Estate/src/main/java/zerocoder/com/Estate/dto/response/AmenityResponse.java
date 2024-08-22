package zerocoder.com.Estate.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.AmenityType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class AmenityResponse extends AbstractResponse implements Serializable {
    private Integer id;
    private String name;
    private AmenityType type;
    private String description;
}
