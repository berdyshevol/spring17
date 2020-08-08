package com.softserve.edu.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @ToString.Exclude
    @NotNull
    @ManyToOne(optional=false)
    @JoinColumn(name="sprint_id")
    private Sprint sprint;

    @ToString.Exclude
    @OneToMany(mappedBy = "task")
    private List<Progress> progresses;


    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;
}
