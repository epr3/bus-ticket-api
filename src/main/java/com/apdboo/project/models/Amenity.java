package com.apdboo.project.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Table(name = "buses")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amenity {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column
    private String name;

    @NotEmpty
    @Column
    private String icon;

    @NotEmpty
    @Column
    private Float priceModifier;

    @NotEmpty
    @Column
    private String priceModifierType;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "amenities")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Bus> buses;
}
