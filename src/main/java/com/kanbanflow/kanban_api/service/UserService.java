package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.GetUserResponseDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserRegistrationResponseDto createUser(UserRegistrationRequestDto requestDto);

    GetUserResponseDto getUserById(Long id);

    UserDetails loadUserByUserName(String userName);
}
