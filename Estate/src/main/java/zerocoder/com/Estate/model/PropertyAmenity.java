package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import zerocoder.com.Estate.enums.AmenityType;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property_amenity")
public class PropertyAmenity extends BaseEntity<Integer> {

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AmenityType type;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "property_id")
    private Property property;
}
