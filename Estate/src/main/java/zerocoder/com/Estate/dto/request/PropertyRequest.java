package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import zerocoder.com.Estate.validator.EnumNamePattern;
import zerocoder.com.Estate.validator.NumberValid;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class PropertyRequest implements Serializable {
    private Long id;

    @EnumNamePattern(regexp = "APARTMENT|HOUSE|VILLA", message = "Loại tài sản không hợp lệ")
    private String type;

    @NotBlank(message = "Tên tòa nhà không được để trống")
    private String name;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Thành phố không được để trống")
    private String city;

    @NotBlank(message = "Quận không được để trống")
    private String district;

    @NotBlank(message = "Phường không được để trống")
    private String ward;

    @NotNull(message = "Diện tích không được để trống")
    @Min(value = 10, message = "Diện tích phải lớn hơn 10m2")
    private Double area;

    @NotNull(message = "Số tầng không được để trống")
    @Min(value = 1, message = "Số tầng phải lớn hơn 1")
    private Integer floor;

    @NotNull(message = "Số phòng ngủ không được để trống")
    @Min(value = 0, message = "Số phòng ngủ phải lớn hơn hoặc bằng 0")
    private Integer bedroom;

    @NotNull(message = "Số phòng tắm không được để trống")
    @Min(value = 0, message = "Số phòng tắm phải lớn hơn hoặc bằng 0")
    private Integer bathroom;

    @NotNull(message = "Năm xây dựng không được để trống")
    @Range(min = 1900, max = 2024, message = "Năm xây dựng phải từ 1900 đến 2024")
    private Integer builtYear;

    @EnumNamePattern(regexp = "NORTH|SOUTH|EAST|WEST", message = "Hướng không hợp lệ")
    private String direction;

    private String description;

    @EnumNamePattern(regexp = "AVAILABLE|RENTED|SOLD|UNDER_REPAIR", message = "Trạng thái không hợp lệ")
    private String status;

    @NotNull(message = "Giá bán không được để trống")
    @Min(value = 50000000, message = "Giá bán phải lớn hơn 50 triệu")
    private Long salePrice;

    @NotNull(message = "Giá thuê không được để trống")
    @Min(value = 1000000, message = "Giá thuê phải lớn hơn 1 triệu")
    private Long rentPrice;
}
