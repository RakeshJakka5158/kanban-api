package com.kanbanflow.kanban_api.controller;

import com.kanbanflow.kanban_api.dto.GetUserResponseDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationResponseDto;
import com.kanbanflow.kanban_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return new ResponseEntity<>(userService.createUser(requestDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponseDto> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
