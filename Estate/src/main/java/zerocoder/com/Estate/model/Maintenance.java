package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import zerocoder.com.Estate.enums.MaintenanceLevel;
import zerocoder.com.Estate.enums.MaintenanceStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "maintenance")
public class Maintenance extends BaseEntity<Long> {
    @Column(name = "description")
    private String description;

    @Column(name = "reported_at")
    private LocalDateTime reportedAt;

    @Column(name = "priority_level")
    private MaintenanceLevel priorityLevel;

    @Column(name = "status")
    private MaintenanceStatus status;

    @Column(name = "estimated_cost")
    private Long estimatedCost;

    @Column(name = "actual_cost")
    private Long actualCost;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

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
    @JoinColumn(name = "reported_by_id")
    private Customer reportedBy;

}
