package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import zerocoder.com.Estate.enums.ActivityType;
import zerocoder.com.Estate.validator.EnumNamePattern;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ActivityRequest implements Serializable {
    private Long id;

    @NotBlank(message = "Điểm đến không được để trống")
    private String location;

    @NotBlank(message = "Ghi chú không được để trống")
    private String note;

    @NotNull(message = "Chi phí không được để trống")
    @Range(min = 0, message = "Chi phí không hợp lệ")
    private Long cost;

    @NotBlank(message = "Kết quả không được để trống")
    private String result;

    @EnumNamePattern(regexp = "MEETING|CONTRACT|OTHER", message = "Loại hoạt động không hợp lệ")
    private String type;

    @NotNull(message = "Thời gian không được để trống")
    private LocalDateTime time;

    @NotNull(message = "Mã khách hàng không được để trống")
    private Long customerId;
}
