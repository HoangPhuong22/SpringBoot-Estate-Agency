package zerocoder.com.Estate.dto.search;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AmenitySearchDTO {
    private String name;
    private String type;
    private Integer pageNo;
    private Integer pageSize;
}
