package zerocoder.com.Estate.model;

import jakarta.persistence.*;
import lombok.*;
import zerocoder.com.Estate.enums.DirectionProperty;
import zerocoder.com.Estate.enums.StatusProperty;
import zerocoder.com.Estate.enums.TypeProperty;

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
    private TypeProperty type;

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
    private DirectionProperty direction;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusProperty status;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "rent_price")
    private String rentPrice;


}
