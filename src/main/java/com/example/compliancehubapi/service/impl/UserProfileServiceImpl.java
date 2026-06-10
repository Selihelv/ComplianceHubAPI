package com.example.compliancehubapi.service.impl;

import com.example.compliancehubapi.model.User;
import com.example.compliancehubapi.model.UserProfile;
import com.example.compliancehubapi.repository.UserProfileRepository;
import com.example.compliancehubapi.repository.UserRepository;
import com.example.compliancehubapi.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    public UserProfile saveUserProfile(UserProfile userProfile) {
        log.info("Saving user profile: {}", userProfile);
        return userProfileRepository.save(userProfile);
    }

    public List<UserProfile> getAllUserProfiles(){
        log.info("Fetching all user profiles");
        return userProfileRepository.findAll();
    }


    public UserProfile getUserProfileById(Long id){
        log.info("Fetching user profile by id {}", id);
        var user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id: " + id + " not found.")
        );

        return user.getUserProfile();
    }


    public Optional<UserProfile> getUserProfileByUserId(Long userId){
        log.info("Fetching user profile by user id {}", userId);
        return userRepository.findById(userId)
                .map(User::getUserProfile);
    }

    public UserProfile updateUserProfile(Long userId, UserProfile updatedUserProfile){
        log.info("Updating user profile with id {}", userId);
       var user= userRepository.findById(userId).orElseThrow(
               () -> new RuntimeException("User with id: " + userId + " not found.")
       );
       var profile = user.getUserProfile();
       profile.setFirstName(updatedUserProfile.getFirstName());
       profile.setLastName(updatedUserProfile.getLastName());
       profile.setEmail(updatedUserProfile.getEmail());
       profile.setMarketplace(updatedUserProfile.getMarketplace());

       return userProfileRepository.save(profile);
    }

}
