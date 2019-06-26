package com.apdboo.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Bus {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "bus")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private List<Itinerary> itineraries;

    @NotEmpty
    @Column
    private String plateNo;

    @NotEmpty
    @Column
    private String busMake;

    @NotEmpty
    @Column
    private String busModel;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "bus_amenities",
            joinColumns = {@JoinColumn(name = "bus_id")},
            inverseJoinColumns = {@JoinColumn(name = "amenity_id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Amenity> amenities;
}
