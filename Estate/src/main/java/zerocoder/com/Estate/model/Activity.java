package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zerocoder.com.Estate.enums.ActivityType;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity")
public class Activity extends BaseEntity<Long> {

    @Column(name = "activity_type")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ActivityType activityType;

    @Column(name = "activity_time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime activityTime;

    @Column(name = "location")
    private String location;

    @Column(name = "result")
    private String result;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
