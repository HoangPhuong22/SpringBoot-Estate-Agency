package zerocoder.com.Estate.dto.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import zerocoder.com.Estate.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class EmployeeResponse extends AbstractResponse {
    private Long id;
    private String code;
    private String fullName;
    private String email;
    private String phone;
    private Gender gender;
    private String idNumber;
    private String address;
    private String education;
    private LocalDate birthDay;
    private LocalDate hireDate;
    private Boolean isActive;
    private Long accountId;
    private String userName;
}
