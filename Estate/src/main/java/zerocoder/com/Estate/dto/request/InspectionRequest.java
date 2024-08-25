package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import zerocoder.com.Estate.validator.EnumNamePattern;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionRequest {

    private Long id;

    @Size(max = 255, message = "Loại kiểm tra không được vượt quá 255 ký tự")
    @NotBlank(message = "Loại kiểm tra không được để trống")
    private String type;

    @NotNull(message = "Id tài sản không được để trống")
    private Long propertyId;

    @NotNull(message = "Ngày kiểm tra không được để trống")
    private LocalDateTime inspectionDate;

    @NotBlank(message = "Báo cáo không được để trống")
    private String report;

    @EnumNamePattern(regexp = "FAILED|PASSED", message = "Trạng thái không hợp lệ")
    private String status;
}
