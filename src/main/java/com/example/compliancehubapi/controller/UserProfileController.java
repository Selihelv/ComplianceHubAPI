package com.example.compliancehubapi.controller;

import com.example.compliancehubapi.model.UserProfile;
import com.example.compliancehubapi.service.UserProfileService;
import com.example.compliancehubapi.service.impl.UserProfileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping ("/{userId}")
    public UserProfile getUserProfileById(@PathVariable Long userId) {
        return userProfileService.getUserProfileById(userId);
    }

    @PutMapping("/{userId}")
    public UserProfile updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile updatedUserProfile) {
    return userProfileService.updateUserProfile(userId, updatedUserProfile);
    }
}
