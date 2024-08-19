package zerocoder.com.Estate.dto.response;

import lombok.*;
import zerocoder.com.Estate.enums.AmenityType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmenityResponse implements Serializable {
    private Integer id;
    private String name;
    private AmenityType type;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
