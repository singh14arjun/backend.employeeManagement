package com.employee.service;

import com.employee.model.User;
import com.employee.payload.dto.UserRegisterDTO;
import com.employee.payload.dto.UserUpdateDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.UserResponseDTO;

import java.util.List;

public interface AdminService {
    public APIResponse<UserResponseDTO> createUser(UserRegisterDTO user);
    public APIResponse<UserResponseDTO> getUserById(Long id);
    public  APIResponse<List<UserResponseDTO>> getAllUser();
    public APIResponse deleteUser(Long id);
    public APIResponse<UserResponseDTO> updateUser(Long id, UserUpdateDTO userUpdateDTO);
}
