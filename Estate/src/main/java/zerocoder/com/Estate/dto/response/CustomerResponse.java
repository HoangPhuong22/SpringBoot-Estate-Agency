package zerocoder.com.Estate.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;
import zerocoder.com.Estate.enums.CustomerStatus;
import zerocoder.com.Estate.enums.CustomerType;
import zerocoder.com.Estate.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@SuperBuilder
public class CustomerResponse extends AbstractResponse implements Serializable {
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
    private String userName;
    private Long accountId;
    private List<Long> employeeIds;
}
