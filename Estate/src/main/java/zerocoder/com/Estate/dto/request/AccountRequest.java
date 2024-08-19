package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerocoder.com.Estate.validator.PasswordValid;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest implements Serializable {
    @NotBlank(message = "Tên đăng nhập không được để trống")
    private String username;

    @PasswordValid(min = 8, message = "Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt")
    private String password;

    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotNull(message = "Id không được để trống")
    private Long id;

    @NotNull(message = "Loại không được để trống")
    private Integer type;
}
