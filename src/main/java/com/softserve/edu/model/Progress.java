package com.softserve.edu.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"task_id", "trainee_id"})})
public class Progress {

    public enum TaskStatus{
        NEW, PASS, FAIL, PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne
    private Task task;

    @ToString.Exclude
    @ManyToOne
    private User trainee;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;

    @CreationTimestamp
    private LocalDate started;

    @UpdateTimestamp
    private LocalDate updated;

}
