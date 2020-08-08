package com.softserve.edu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@ToString(exclude = {"sprints", "users"})
@Entity
public class Marathon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The 'title' cannot be empty")
    private String title;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="marathon", cascade = CascadeType.REMOVE)
    private Set<Sprint> sprints = new LinkedHashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade={CascadeType.PERSIST})
    @JoinTable(
            name="marathon_user", joinColumns=@JoinColumn(name="marathon_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private Set<User> users = new LinkedHashSet<>();
}
