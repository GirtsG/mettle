package com.accenture.mettle.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserFeatureKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "feature_id")
    private Long featureId;
}
