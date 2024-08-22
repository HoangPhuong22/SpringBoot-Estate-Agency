package zerocoder.com.Estate.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.PropertyDirection;
import zerocoder.com.Estate.enums.PropertyStatus;
import zerocoder.com.Estate.enums.PropertyType;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class PropertyResponse extends AbstractResponse {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String city;
    private String district;
    private String ward;
    private Double area;
    private Integer floor;
    private Integer bedRooms;
    private Integer bathRooms;
    private Integer builtYear;
    private String description;
    private PropertyDirection direction;
    private PropertyType type;
    private PropertyStatus status;
    private Long salePrice;
    private Long rentPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
