package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.dto.GetUserResponseDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationResponseDto;
import com.kanbanflow.kanban_api.entity.User;
import com.kanbanflow.kanban_api.exception.ResourceNotFoundException;
import com.kanbanflow.kanban_api.exception.UserAlreadyExistsException;
import com.kanbanflow.kanban_api.repository.UserRepository;
import com.kanbanflow.kanban_api.service.NotificationService;
import com.kanbanflow.kanban_api.service.UserService;
import com.kanbanflow.kanban_api.service.KafkaProducerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaProducerService kafkaProducerService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, NotificationService notificationService, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaProducerService = kafkaProducerService;
    }

    // Creating new user
    @Override
    public UserRegistrationResponseDto createUser(UserRegistrationRequestDto requestDto) {
        // Check if user with username already exists
        Optional<User> existingUser = userRepository.findByUsername(requestDto.getUsername());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User with "+requestDto.getUsername()+" already exists");
        }

        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setEmail(requestDto.getEmail());
        user.setRole(requestDto.getRole());
        // Hash the password using password encoder and set to entity
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        // Save the User
        User savedUser = userRepository.save(user);

        // Send email notification asynchronously
        logger.info("User {} created. Calling notification service on thread: {}", savedUser.getUsername(), Thread.currentThread().getName());
        kafkaProducerService.sendUserCreatedEvent(requestDto);
        logger.info("Returning created user from UserService. Request thread is now free.");

        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto();
        responseDto.setUserName(savedUser.getUsername());
        responseDto.setStatus(HttpStatus.CREATED.value());
        return responseDto;
    }

    @Override
    public GetUserResponseDto getUserById(Long id) {
        return GetUserResponseDto.from(
                userRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with id : "+id))
        );
    }

    @Override
    public UserDetails loadUserByUserName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userName : "+userName));
    }

}
