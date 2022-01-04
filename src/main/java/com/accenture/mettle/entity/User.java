package com.accenture.mettle.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "user")
    private Set<UserFeature> userFeatures;
}
