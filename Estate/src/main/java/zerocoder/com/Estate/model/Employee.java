package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee extends BaseEntity<Long> {

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

    @Column(name = "education")
    private String education;

    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private LocalDate hireDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Assignment> assignments;
}
