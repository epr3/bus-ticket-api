package com.apdboo.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Table(name="intervals")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Interval {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column
    private String intervalStart;

    @NotEmpty
    @Column
    private String intervalEnd;

}
