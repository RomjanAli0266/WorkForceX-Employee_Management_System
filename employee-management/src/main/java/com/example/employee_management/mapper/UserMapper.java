package com.example.employee_management.mapper;

import com.example.employee_management.dto.UserDTO;
import com.example.employee_management.dto.UserRequestDTO;
import com.example.employee_management.entity.UserEntity;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequestDTO request) {
        UserEntity user = new UserEntity();

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        return user;
    }

    public UserDTO toDTO(UserEntity user) {

        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());

        return dto;

    }
}
