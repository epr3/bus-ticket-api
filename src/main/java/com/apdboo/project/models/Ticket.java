package com.apdboo.project.models;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name="tickets")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(insertable = false, updatable = false)
    private Long passenger_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "passenger_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Passenger passenger;

    @Column
    @NotEmpty
    private String code;

    @Column
    @NotEmpty
    private Float total;

    @Column
    @NotEmpty
    @Temporal(TemporalType.DATE)
    private Date ticket_date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bus_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Bus bus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "route_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "interval_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Interval interval;

    @Column(insertable = false, updatable = false)
    private Long bus_id;

    @Column(insertable = false, updatable = false)
    private Long route_id;

    @Column(insertable = false, updatable = false)
    private Long interval_id;

}
