package zerocoder.com.Estate.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import zerocoder.com.Estate.validator.Age;
import zerocoder.com.Estate.validator.EnumNamePattern;
import zerocoder.com.Estate.validator.IdNumberValid;
import zerocoder.com.Estate.validator.PhoneValid;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private Long id;

    @NotBlank(message = "Tên nhân viên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @PhoneValid
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @Age(value = 18, message = "Tuổi phải lớn hơn hoặc bằng 18")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @EnumNamePattern(regexp = "MALE|FEMALE|OTHER", message = "Giới tính không hợp lệ")
    private String gender;

    @IdNumberValid(message = "Số CCCD không hợp lệ")
    private String idNumber;

    @NotBlank(message = "Trình độ học vấn không được để trống")
    private String education;

    @NotNull(message = "Ngày vào làm không được để trống")
    @Temporal(TemporalType.DATE)
    private LocalDate hireDate;

    private Boolean isActive;
}
