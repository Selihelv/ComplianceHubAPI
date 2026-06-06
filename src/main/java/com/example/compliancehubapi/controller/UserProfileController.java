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
    @ResponseStatus(HttpStatus.OK)
    public List<UserProfile> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping ("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfile getUserProfileById(@PathVariable Long userId) {
        return userProfileService.getUserProfileById(userId);
    }

    @GetMapping("/by-user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserProfile> getUserProfileByUserId(@PathVariable Long userId) {
        return userProfileService.getUserProfileByUserId(userId);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfile updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile updatedUserProfile) {
    return userProfileService.updateUserProfile(userId, updatedUserProfile);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserProfileById(@PathVariable Long userId) {
        userProfileService.deleteUserProfileById(userId);
    }
}
