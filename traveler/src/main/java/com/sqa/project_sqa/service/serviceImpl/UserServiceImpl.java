package com.sqa.project_sqa.service.serviceImpl;

import com.sqa.project_sqa.constants.RegexConstants;
import com.sqa.project_sqa.entities.ERole;
import com.sqa.project_sqa.entities.User;
import com.sqa.project_sqa.payload.request.LoginRequest;
import com.sqa.project_sqa.payload.request.SignupRequest;
import com.sqa.project_sqa.payload.response.JwtResponse;
import com.sqa.project_sqa.repositories.UserRepository;
import com.sqa.project_sqa.security.CustomUserDetailsService;
import com.sqa.project_sqa.security.jwt.JwtUtil;
import com.sqa.project_sqa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> signup(SignupRequest signupRequest) {
        log.info("Inside save client {}",signupRequest);
        try {
            if(validateSignUp(signupRequest)){

                if (userRepository.existsByEmail(signupRequest.getEmail())) {
                    return new ResponseEntity<>("Email is already registered", HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByPhone(signupRequest.getPhone())) {
                    return new ResponseEntity<>("Phone number is already registered", HttpStatus.BAD_REQUEST);
                }

                if (userRepository.existsByUserName(signupRequest.getPhone())) {
                    return new ResponseEntity<>("User name is already registered", HttpStatus.BAD_REQUEST);
                }
                User user = modelMapper.map(signupRequest, User.class);
                user.setPassword(encoder.encode(signupRequest.getPassword()));
                user.setRole(ERole.ROLE_USER);

                userRepository.save(user);

            }else {
                return new ResponseEntity<>("Invalid Data",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Register client successfully", HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        log.info("Inside login {}",loginRequest);
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            String jwt = jwtUtil.generateToken(customUserDetailsService.getUserDetail());
            JwtResponse response = new JwtResponse();
            response.setJwt(jwt);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (AuthenticationException exception) {
            return new ResponseEntity<>("Wrong login name or password.", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateSignUp(SignupRequest signupRequest){
        if (StringUtils.isEmpty(signupRequest.getEmail())) {
            log.info("Email is required");
            return false;
        }

        if (StringUtils.isEmpty(signupRequest.getPassword())) {
            log.info("Password is required");
            return false;
        }

        if (StringUtils.isEmpty(signupRequest.getName())) {
            log.info("Name is required");
            return false;
        }

        if (StringUtils.isEmpty(signupRequest.getPhone())) {
            log.info("Phone number is required");
            return false;
        }
        if (!isValidEmail(signupRequest.getEmail())) {
            log.info("Email is not valid");
            return false;
        }

        if (!isValidPassword(signupRequest.getPassword())) {
            log.info("Password is not strong enough");
            return false;
        }
        return true;
    }
    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(RegexConstants.EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(RegexConstants.PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
