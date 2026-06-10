package com.example.compliancehubapi.tools;

import com.example.compliancehubapi.model.UserProfile;
import com.example.compliancehubapi.repository.UserProfileRepository;
import com.example.compliancehubapi.repository.UserRepository;
import com.example.compliancehubapi.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserProfileTools {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Tool(description = "Get all User Profiles")
    public List<UserProfile> getAllUserProfiles(){
        return userProfileRepository.findAll();
    }

    @Tool(description = "Get User Profile by id")
    public Optional<UserProfile> getUserProfileById(Long id){
        return userProfileRepository.findById(id);
    }

    @Tool(description = "Update UserProfile by userId")
    public UserProfile updateUserProfile(Long userId, UserProfile updatedUserProfile){
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
