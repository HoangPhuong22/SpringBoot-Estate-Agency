package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zerocoder.com.Estate.enums.Gender;

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

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDay;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Gender gender;

    @Column(name = "education")
    private String education;

    @Column(name = "hire_date")
    @Temporal(TemporalType.DATE)
    private LocalDate hireDate;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "assignment",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Inspection> inspections;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activity> activities;
}
