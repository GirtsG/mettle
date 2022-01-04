package com.accenture.mettle.repository;

import com.accenture.mettle.entity.UserFeature;
import com.accenture.mettle.entity.UserFeatureKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFeatureRepository extends JpaRepository<UserFeature, UserFeatureKey> {

    UserFeature findByUserNameAndFeatureFeatureId(String userName, Long featureId);

    List<UserFeature> findByEnabled(Boolean enabled);
}
