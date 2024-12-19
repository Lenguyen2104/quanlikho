package com.security.duanspringboot.controller;

import com.security.duanspringboot.dto.request.authen.ChangePasswordRequest;
import com.security.duanspringboot.dto.request.authen.RefreshTokenRequest;
import com.security.duanspringboot.dto.request.authen.SignInRequest;
import com.security.duanspringboot.dto.request.authen.SignUpUserRequest;
import com.security.duanspringboot.exception.ServiceSecurityException;
import com.security.duanspringboot.service.AuthenticationService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/un_auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final Validator validator;

    @PostMapping("/signup/user")
    public ResponseEntity<Object> signUpUser(@RequestBody SignUpUserRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(authenticationService.registerUser(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@RequestBody RefreshTokenRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(authenticationService.changePassword(request));
    }

    private <T> void validateRequest(T request) {
        var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new ServiceSecurityException(violations);
    }
}
