package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.model.UserProfile;
import com.example.compliancehubapi.service.UserProfileService;
import com.example.compliancehubapi.service.impl.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfile saveUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.saveUserProfile(userProfile);
    }

    @GetMapping
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping ("/{userId}")
    public UserProfile getUserProfileById(@PathVariable Long userId) {
        return userProfileService.getUserProfileById(userId);
    }

    @GetMapping
    public Optional<UserProfile> getUserProfileByUserId(@PathVariable Long userId) {
        return userProfileService.getUserProfileByUserId(userId);
    }

    @PutMapping("/{userId}")
    public UserProfile updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile updatedUserProfile) {
    return userProfileService.updateUserProfile(userId, updatedUserProfile);
    }
}
