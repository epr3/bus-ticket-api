package com.apdboo.project.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
    @Temporal(TemporalType.TIME)
    private Date intervalStart;

    @NotEmpty
    @Column
    @Temporal(TemporalType.TIME)
    private Date intervalEnd;

}
