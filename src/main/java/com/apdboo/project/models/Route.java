package com.apdboo.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Table(name="routes")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotEmpty
    private Float price;

    @Column
    @NotEmpty
    private Integer distance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_start_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private City startCity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_end_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private City endCity;

    @OneToMany(mappedBy = "route")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Itinerary> busRouteIntervals;
}
