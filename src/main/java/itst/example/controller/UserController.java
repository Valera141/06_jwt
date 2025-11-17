package itst.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import itst.example.dto.UserLoginRequest;
import itst.example.dto.UserLoginResponse;
import itst.example.dto.UserRequest;
import itst.example.dto.UserResponse;
import itst.example.mapper.UserLoginMapper;
import itst.example.model.User;
import itst.example.service.JwtService;
import itst.example.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public UserResponse create(@RequestBody UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserResponse created = service.create(user);
        return created;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody UserLoginRequest loginRequest) {             
        User authenticatedUser = service.authenticate(loginRequest);        
        UserLoginResponse userLoginResponse = UserLoginMapper.toResponse(authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        userLoginResponse.setToken(jwtToken);
        userLoginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(userLoginResponse);
    }

    /*@PostMapping("/login")
    public ResponseEntity<UserResponse> authenticate(@RequestBody UserRequest request) {
        User authenticatedUser = service.authenticate(request);
        System.out.println(authenticatedUser.getEmail());
        String jwtToken = jwtService.generateToken(authenticatedUser);
        UserResponse userResponse = UserResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();
        return ResponseEntity.ok(userResponse);
    }*/
}
