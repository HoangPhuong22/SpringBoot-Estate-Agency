package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import zerocoder.com.Estate.enums.CustomerType;

import java.time.LocalDate;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "reportedBy", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;
}
