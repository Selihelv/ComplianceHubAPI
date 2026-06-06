package com.example.compliancehubapi.service;

import com.example.compliancehubapi.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {

    UserProfile saveUserProfile(UserProfile userProfile);

    List<UserProfile> getAllUserProfiles();

    UserProfile getUserProfileById(Long id);

    Optional<UserProfile> getUserProfileByUserId(Long userId);

    UserProfile updateUserProfile(Long id, UserProfile userProfile);

    void deleteUserProfileById(Long userId);
}
