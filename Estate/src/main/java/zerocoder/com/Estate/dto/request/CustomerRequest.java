package zerocoder.com.Estate.dto.request;

import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerocoder.com.Estate.validator.Age;
import zerocoder.com.Estate.validator.EnumNamePattern;
import zerocoder.com.Estate.validator.IdNumberValid;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest implements Serializable {
    private Long id;

    @NotBlank(message = "Tên khách hàng không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @EnumNamePattern(regexp = "MALE|FEMALE|OTHER", message = "Giới tính không hợp lệ")
    private String gender;

    @Age(value = 18, message = "Tuổi phải lớn hơn hoặc bằng 18")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @IdNumberValid(message = "Số căn cước không hợp lệ")
    private String idNumber;

    @EnumNamePattern(regexp = "INDIVIDUAL|COMPANY|OTHER", message = "Loại khách hàng không hợp lệ")
    private String type;

    @EnumNamePattern(regexp = "PENDING|ASSIGNED|DONE", message = "Trạng thái không hợp lệ")
    private String status = "PENDING";

}
