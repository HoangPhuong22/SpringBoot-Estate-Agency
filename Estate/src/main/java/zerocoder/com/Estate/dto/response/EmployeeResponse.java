package zerocoder.com.Estate.dto.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import zerocoder.com.Estate.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String code;
    private String fullName;
    private String email;
    private String phone;
    private Gender gender;
    private String idNumber;
    private String address;
    private String education;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    private LocalDate hireDate;
    private Boolean isActive;
    private Long createdBy;
    private Long updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
    private Long accountId;
    private String userName;
}
