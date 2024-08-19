package zerocoder.com.Estate.dto.response;

import lombok.*;
import zerocoder.com.Estate.enums.CustomerStatus;
import zerocoder.com.Estate.enums.CustomerType;
import zerocoder.com.Estate.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse implements Serializable {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String idNumber;
    private String address;
    private Gender gender;
    private CustomerStatus status;
    private CustomerType type;
    private LocalDate birthDay;
    private Long createdBy;
    private Long updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String userName;
    private Long accountId;
}
