package com.alfonsosarno.fonzy.controllers;

import com.alfonsosarno.fonzy.entities.Abbonamento;
import com.alfonsosarno.fonzy.entities.User;
import com.alfonsosarno.fonzy.payloads.UserRequestDTO;
import com.alfonsosarno.fonzy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
public Page<User> findAll(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue =  "10") int size,
                          @RequestParam(defaultValue = "id") String sortBy){
        return this.userService.findAll(page, size, sortBy);

    }

@GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser){
        return currentAuthenticatedUser;
}

@PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody UserRequestDTO body){
        return this.userService.findByIdAndUpdate(currentAuthenticatedUser.getIdUser(), body);
}


    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.userService.findByIdAndDelete  (currentAuthenticatedUser.getIdUser());
    }


    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return this.userService.findById(userId);
    }


    @PutMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable UUID userId, @RequestBody UserRequestDTO body) {
        return this.userService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID userId) {
        this.userService.findByIdAndDelete(userId);
    }


    @GetMapping("/{userId}/subscriptions")
    public List<Abbonamento> getUserSubscriptions(@PathVariable UUID userId) {
        User user = userService.findById(userId);
        return user.getAbbonamenti();
    }
}
