package zerocoder.com.Estate.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
public class AccountResponse extends AbstractResponse implements Serializable {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String roles;
    private String status;
}
