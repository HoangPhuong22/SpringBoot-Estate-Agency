package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import zerocoder.com.Estate.enums.InspectionStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inspection")
public class Inspection extends BaseEntity<Long>{
    @Column(name = "type")
    private String type;

    @Column(name = "inspection_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime inspectionDate;

    @Column(name = "report")
    private String report;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private InspectionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "inspector_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "property_id")
    private Property property;
}
