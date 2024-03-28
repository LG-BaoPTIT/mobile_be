package com.sqa.project_sqa.service;


import com.sqa.project_sqa.payload.request.LoginRequest;
import com.sqa.project_sqa.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> signup(SignupRequest signupRequest);
    ResponseEntity<?> login(LoginRequest loginRequest);

}