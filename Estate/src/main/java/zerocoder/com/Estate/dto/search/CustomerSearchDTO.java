package zerocoder.com.Estate.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchDTO {
    private String fullName;
    private String email;
    private String phone;
    private String idNumber;
    private String address;
    private String userNameEmployee;
    private Long employeeId;
    private Integer pageNo;
    private Integer pageSize;
}
