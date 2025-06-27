package com.mwu.backend.controller;

import com.mwu.backend.config.JwtUtil;
import com.mwu.backend.models.User;
import com.mwu.backend.repositories.UserRepository;
import com.mwu.backend.requests.LoginRequest;
import com.mwu.backend.requests.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

     @PostMapping("/login")
     public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
         try {
             authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
             );
             return new ResponseEntity<>(jwtUtil.generateToken(
                     loginRequest.getEmail(),
                     userRepository.findByEmail(loginRequest.getEmail()).getId(),
                        userRepository.findByEmail(loginRequest.getEmail()).getName() + " " + userRepository.findByEmail(loginRequest.getEmail()).getLastName()
                ), org.springframework.http.HttpStatus.OK);
         } catch (Exception e) {
                return new ResponseEntity<>("Invalid email or password", org.springframework.http.HttpStatus.UNAUTHORIZED);
         }

     }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()) != null){
            return new ResponseEntity<>("Email already exists", org.springframework.http.HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword())
        );
        return new ResponseEntity<>(jwtUtil.generateToken(
                registerRequest.getEmail(),
                userRepository.findByEmail(registerRequest.getEmail()).getId(),
                registerRequest.getName() + " " + registerRequest.getLastName()
        ), org.springframework.http.HttpStatus.OK);

    }
}
