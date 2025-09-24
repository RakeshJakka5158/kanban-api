package com.kanbanflow.kanban_api.controller;

import com.kanbanflow.kanban_api.dto.LoginRequestDto;
import com.kanbanflow.kanban_api.dto.LoginResponseDto;
import com.kanbanflow.kanban_api.entity.User;
import com.kanbanflow.kanban_api.service.UserService;
import com.kanbanflow.kanban_api.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> createAuthenticationToken(@RequestBody @Valid LoginRequestDto request) throws Exception {
        // Authentication by AuthenticationManager
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword());
            authenticationManager.authenticate(authentication);
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password");
        }

        // Token generation
        UserDetails userDetails = userService.loadUserByUserName(request.getUserName());
        String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponseDto(jwtToken));
    }


}
