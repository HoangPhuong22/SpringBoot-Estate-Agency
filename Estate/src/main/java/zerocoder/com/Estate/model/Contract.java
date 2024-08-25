package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zerocoder.com.Estate.enums.ContractStatus;
import zerocoder.com.Estate.enums.ContractType;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract")
public class Contract extends BaseEntity<Long>{

    @Column(name = "code")
    private String code;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ContractType type;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate endDate;

    @Column(name = "value")
    private Long value;

    @Column(name = "deposit")
    private Long deposit;

    @Column(name = "service_fee")
    private Long serviceFee;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ContractStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
