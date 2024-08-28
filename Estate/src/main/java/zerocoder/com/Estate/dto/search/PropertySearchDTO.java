package zerocoder.com.Estate.dto.search;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertySearchDTO {
    private String name;
    private Integer builtYear;
    private String type;
    private String status;
    private Double area;
    private Integer bedroom;
    private Integer bathroom;
    private Integer floor;
    private Long salePrice;
    private Long rentPrice;
    private String address;
    private String city;
    private String district;
    private String ward;
    private Integer pageNo;
    private Integer pageSize;
    private Long customerId;
    private Integer isDeleted;
}
