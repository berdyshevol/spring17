package com.softserve.edu.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity(name="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    @Column(unique = true)
    @NotBlank
    @Pattern(regexp=".+@.+\\..+", message = "Please provide a valid email address")
    private String email;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @NotBlank
    private String password;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<Marathon> marathons;

    @OneToMany(mappedBy = "trainee")
    private List<Progress> progresses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
