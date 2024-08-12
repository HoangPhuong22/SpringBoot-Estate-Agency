package zerocoder.com.Estate.model;

import jakarta.persistence.Column;

public class Role extends BaseEntity<Integer> {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
