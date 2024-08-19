package zerocoder.com.Estate.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zerocoder.com.Estate.validator.PasswordValid;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest implements Serializable {
    @NotNull(message = "Id không được để trống")
    private Long id;

    @PasswordValid(min = 8)
    private String passwordChange;
}
