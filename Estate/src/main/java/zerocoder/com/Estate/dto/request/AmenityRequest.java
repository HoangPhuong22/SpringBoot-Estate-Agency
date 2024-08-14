package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerocoder.com.Estate.validator.EnumNamePattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequest {

    private Integer id;

    @NotBlank(message = "Tên tiện ích không được để trống")
    private String name;

    @EnumNamePattern(regexp = "COMMON|OTHER|EXTERIOR|INTERIOR", message = "Loại tiện ích không hợp lệ")
    private String type;

    private String description;
}
