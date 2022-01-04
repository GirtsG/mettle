package com.accenture.mettle.controller;

import com.accenture.mettle.entity.Feature;
import com.accenture.mettle.entity.User;
import com.accenture.mettle.entity.UserFeature;
import com.accenture.mettle.entity.UserFeatureKey;
import com.accenture.mettle.model.FeatureModel;
import com.accenture.mettle.model.FeaturesModel;
import com.accenture.mettle.repository.FeatureRepository;
import com.accenture.mettle.repository.UserFeatureRepository;
import com.accenture.mettle.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
@AllArgsConstructor
class HomeController {

    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;
    private final UserFeatureRepository userFeatureRepository;

    @PostMapping("features/add")
    String addFeature(@RequestParam String featureName, ModelMap model) {
        Feature feature = new Feature();
        feature.setName(featureName);
        featureRepository.saveAndFlush(feature);
        userFeatureRepository.saveAllAndFlush(createFeatureForUsers(feature));
        model.clear();
        return "redirect:/";
    }

    @GetMapping("features/view")
    String viewFeatures() {
        return "home";
    }

    @PostMapping("features/update")
    String updateFeatures(@ModelAttribute FeaturesModel model) {
        List<UserFeature> userFeatures = new ArrayList<>();
        for (FeatureModel feature : model.getFeatures()) {
            feature.getFeatureId();
            UserFeature userFeature = userFeatureRepository.findByUserNameAndFeatureFeatureId(feature.getUserSelected(), feature.getFeatureId());
            userFeature.setEnabled(feature.getFeatureEnabled());
            userFeatures.add(userFeature);
        }
        userFeatureRepository.saveAllAndFlush(userFeatures);
        return "home";
    }

    @GetMapping("features/enabled")
    String showEnabledFeatures(Model model) {
        model.addAttribute("enabledUserFeatures", userFeatureRepository.findByEnabled(Boolean.TRUE));
        return "home";
    }

    @ModelAttribute("allFeatures")
    public FeaturesModel allFeatures() {
        List<Feature> features = featureRepository.findAll();
        List<FeatureModel> models = new ArrayList<>();
        for (Feature feature : features) {
            models.add(createFeatureModel(feature));
        }
        return new FeaturesModel(models);
    }

    private FeatureModel createFeatureModel(Feature feature) {
        String userSelected = null;
        Boolean userEnabled = Boolean.FALSE;
        for (UserFeature userFeature : feature.getUserFeatures()) {
            if ("admin".equals(userFeature.getUser().getName())) {
                userSelected = userFeature.getUser().getName();
                userEnabled = userFeature.getEnabled();
                break;
            }
        }
        return new FeatureModel(
                feature.getFeatureId(),
                feature.getName(),
                createFeatureUsersMap(feature),
                userSelected,
                userEnabled
        );
    }

    private Map<String, Boolean> createFeatureUsersMap(Feature feature) {
        Map<String, Boolean> map = new HashMap<>();
        for (UserFeature userFeature : feature.getUserFeatures()) {
            map.put(userFeature.getUser().getName(), userFeature.getEnabled());
        }
        return map;
    }

    private List<UserFeature> createFeatureForUsers(Feature feature) {
        List<UserFeature> userFeatures = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserFeature userFeature = new UserFeature();
            UserFeatureKey userFeatureKey = new UserFeatureKey();
            userFeatureKey.setFeatureId(feature.getFeatureId());
            userFeatureKey.setUserId(user.getUserId());
            userFeature.setId(userFeatureKey);
            userFeature.setFeature(feature);
            userFeature.setUser(user);
            userFeatures.add(userFeature);
        }
        return userFeatures;
    }
}
