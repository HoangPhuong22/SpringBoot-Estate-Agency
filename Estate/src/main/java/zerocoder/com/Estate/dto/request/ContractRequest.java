package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerocoder.com.Estate.validator.EnumNamePattern;

import java.io.Serializable;
import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest implements Serializable {
    private Long id;

    @NotNull(message = "Id tài sản không được để trống")
    private Long propertyId;

    @NotNull(message = "Id khách hàng không được để trống")
    private Long customerId;

    @EnumNamePattern(regexp = "SALE|RENT", message = "Loại hợp đồng không hợp lệ")
    private String contractType;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    private LocalDate startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    private LocalDate endDate;

    @NotNull(message = "Giá trị hợp đồng không được để trống")
    private Long value;
}
