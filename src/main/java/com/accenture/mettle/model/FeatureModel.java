package com.accenture.mettle.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureModel implements Serializable {

    private Long featureId;
    private String name;
    private Map<String, Boolean> users;
    private String userSelected;
    private Boolean featureEnabled;
}
