package com.sqa.project_sqa.controller;

import com.sqa.project_sqa.payload.request.LoginRequest;
import com.sqa.project_sqa.payload.request.SignupRequest;
import com.sqa.project_sqa.service.UserService;
import com.sqa.project_sqa.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequest signupRequest){
        try{
            return userService.signup(signupRequest);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.getResponseEntity("05","Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            return userService.login(loginRequest);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
