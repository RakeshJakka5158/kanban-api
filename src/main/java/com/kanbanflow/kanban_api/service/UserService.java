package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.GetUserResponseDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationResponseDto;
import com.kanbanflow.kanban_api.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {

    UserRegistrationResponseDto createUser(UserRegistrationRequestDto requestDto);
    GetUserResponseDto getUserById(Long id);

    UserDetails loadUserByUserName(@NotNull(message = "UserName cannot be null") @Size(min = 3, max = 10 , message = "Size should be in between 3 and 10") String userName);
}
