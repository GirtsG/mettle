package com.accenture.mettle.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFeature {

    @EmbeddedId
    private UserFeatureKey id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    @MapsId("featureId")
    @ManyToOne
    @JoinColumn(name = "feature_id", updatable = false, insertable = false)
    private Feature feature;

    @Column(columnDefinition = "boolean default false")
    private Boolean enabled = false;
}
