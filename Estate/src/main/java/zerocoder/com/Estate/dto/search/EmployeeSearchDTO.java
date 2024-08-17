package zerocoder.com.Estate.dto.search;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchDTO {
    private String fullName;
    private String email;
    private String phone;
    private String idNumber;
    private Integer pageNo;
    private Integer pageSize;
}
