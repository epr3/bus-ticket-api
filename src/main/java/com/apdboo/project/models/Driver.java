package com.apdboo.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Table(name = "drivers")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Bus bus;

    @NotEmpty
    @Column
    private String name;

    @NotEmpty
    @Column
    private String surname;
}
