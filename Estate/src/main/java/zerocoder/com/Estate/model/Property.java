package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import zerocoder.com.Estate.enums.PropertyDirection;
import zerocoder.com.Estate.enums.PropertyStatus;
import zerocoder.com.Estate.enums.PropertyType;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property")
public class Property extends BaseEntity<Long> {

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private PropertyType type;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "area")
    private Double area;

    @Column(name = "bedrooms")
    private Integer bedRooms;

    @Column(name = "bathrooms")
    private Integer bathRooms;

    @Column(name = "built_year")
    private Integer builtYear;

    @Column(name = "direction")
    @Enumerated(EnumType.STRING)
    private PropertyDirection direction;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "rent_price")
    private String rentPrice;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contract> contracts;
}
