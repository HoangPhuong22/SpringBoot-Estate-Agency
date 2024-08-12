package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property_image")
public class PropertyImage extends BaseEntity<Integer> {

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_main")
    private Boolean isMain;

    @Column(name = "description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "property_id")
    private Property property;
}
