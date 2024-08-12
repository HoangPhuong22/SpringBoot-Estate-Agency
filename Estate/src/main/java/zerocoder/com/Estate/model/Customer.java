package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import zerocoder.com.Estate.enums.CustomerType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity<Long> {

    @Column(name = "code")
    private String code;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_day")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CustomerType type;

}
