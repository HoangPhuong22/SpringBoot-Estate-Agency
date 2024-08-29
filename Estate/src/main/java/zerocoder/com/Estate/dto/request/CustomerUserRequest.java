package zerocoder.com.Estate.dto.request;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerocoder.com.Estate.validator.Age;
import zerocoder.com.Estate.validator.EnumNamePattern;
import zerocoder.com.Estate.validator.PhoneValid;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUserRequest {

    @NotBlank(message = "Tên không được để trống")
    private String fullName;

    @PhoneValid(message = "Số điện thoại không hợp lệ")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    private String demand;

    @EnumNamePattern(regexp = "MALE|FEMALE|OTHER", message = "Giới tính không hợp lệ")
    private String gender;

    @Age(value = 18, message = "Tuổi phải lớn hơn hoặc bằng 18")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

}
