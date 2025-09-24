package com.kanbanflow.kanban_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotNull(message = "UserName cannot be null")
    @Size(min = 3, max = 10 , message = "Size should be in between 3 and 10")
    private String userName;
    @NotNull(message = "Password cannot be null")
    private String password;
}
