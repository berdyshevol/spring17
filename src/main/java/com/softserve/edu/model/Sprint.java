package com.softserve.edu.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish")
    private LocalDate endDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "marathon_id", nullable = false)
    private Marathon marathon;

    @OneToMany(mappedBy = "sprint")
    private List<Task> tasks;
}
