package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.model.UserProfile;
import com.example.compliancehubapi.service.UserProfileService;
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

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfile getUserProfileById(@PathVariable Long id) {
        return userProfileService.getUserProfileById(id);
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

}
