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
public class Feature {

    @Id
    @GeneratedValue
    private Long featureId;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "feature")
    private Set<UserFeature> userFeatures;
}
