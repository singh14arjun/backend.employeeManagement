package com.employee.service.impl;

import com.employee.constant.Department;
import com.employee.constant.Role;
import com.employee.exception.EmailAlreadyExistsException;
import com.employee.exception.PhoneNumberAlreadyExistsException;
import com.employee.model.User;
import com.employee.payload.dto.UserRegisterDTO;
import com.employee.payload.dto.UserUpdateDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.UserResponseDTO;
import com.employee.repository.AdminRepository;
import com.employee.security.JwtService;
import com.employee.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public APIResponse<UserResponseDTO> createUser(UserRegisterDTO userRegisterDTO) {

        Optional<User> emailUser = adminRepository.findByEmail(userRegisterDTO.getEmail());

        if (emailUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Optional<User> phoneUser = adminRepository.findByPhoneNumber(userRegisterDTO.getPhoneNumber());

        if (phoneUser.isPresent()) {
            throw new PhoneNumberAlreadyExistsException("Phone number already exists.");
        }

        User newUser = new User();
        newUser.setFirstName(userRegisterDTO.getFirstName());
        newUser.setLastName(userRegisterDTO.getLastName());
        newUser.setRole(Role.ADMIN);
        newUser.setDepartment(Department.HR);
        newUser.setSalary(userRegisterDTO.getSalary());
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        newUser.setProfileImage(userRegisterDTO.getProfileImage());
        newUser.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        newUser.setAddress(userRegisterDTO.getAddress());
        newUser.setDob(userRegisterDTO.getDob());
        newUser.setGender(userRegisterDTO.getGender());
        newUser.setJobTitle(userRegisterDTO.getJobTitle());
        newUser.setJoiningDate(LocalDate.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = adminRepository.save(newUser);

        UserResponseDTO userResponseDTO = mapToResponse(savedUser);

        return new APIResponse(
                true,
                "Employee created successfully",
                mapToResponse(savedUser));
    }

    private static UserResponseDTO mapToResponse(User savedUser) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(savedUser.getId());
        userResponseDTO.setFirstName(savedUser.getFirstName());
        userResponseDTO.setLastName(savedUser.getLastName());
        userResponseDTO.setRole(savedUser.getRole());
        userResponseDTO.setDepartment(savedUser.getDepartment());
        userResponseDTO.setSalary(savedUser.getSalary());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setProfileImage(savedUser.getProfileImage());
        userResponseDTO.setPhoneNumber(savedUser.getPhoneNumber());
        userResponseDTO.setAddress(savedUser.getAddress());
        userResponseDTO.setDob(savedUser.getDob());
        userResponseDTO.setGender(savedUser.getGender());
        userResponseDTO.setJobTitle(savedUser.getJobTitle());
        userResponseDTO.setJoiningDate(savedUser.getJoiningDate());
        return userResponseDTO;
    }

    @Override
    public APIResponse<UserResponseDTO> getUserById(Long id) {

        Optional<User> optionalUser = adminRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new APIResponse<>(
                    false,
                    "User not found with id: " + id,
                    null);
        }

        UserResponseDTO responseDTO = mapToResponse(optionalUser.get());

        return new APIResponse<>(
                true,
                "User found",
                responseDTO);
    }

    @Override
    public APIResponse<List<UserResponseDTO>> getAllUser() {

        List<User> users = adminRepository.findAll();

        if (users.isEmpty()) {
            return new APIResponse<>(
                    true,
                    "No User found",
                    List.of());
        }

        List<UserResponseDTO> userResponseDTOS = users
                .stream()
                .map(AdminServiceImpl::mapToResponse).toList();
        return new APIResponse<>(
                true,
                "User list",
                userResponseDTOS);
    }

    @Override
    public APIResponse<String> deleteUser(Long id) {

        Optional<User> optionalUser = adminRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return new APIResponse<>(
                    false,
                    "User not found with id: " + id,
                    null);
        }

        adminRepository.delete(optionalUser.get());

        return new APIResponse<>(
                true,
                "User deleted successfully.",
                "Deleted user with ID: " + id);
    }

    @Override
    public APIResponse<UserResponseDTO> updateUser(Long id, UserUpdateDTO userDto) {

        User existingUser = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id : " + id));

        Optional<User> emailUser = adminRepository.findByEmail(userDto.getEmail());

        if (emailUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Optional<User> phoneUser = adminRepository.findByPhoneNumber(userDto.getPhoneNumber());

        if (phoneUser.isPresent()) {
            throw new PhoneNumberAlreadyExistsException("Phone number already exists.");
        }

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setRole(userDto.getRole());
        existingUser.setDepartment(userDto.getDepartment());
        existingUser.setSalary(userDto.getSalary());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setAddress(userDto.getAddress());
        existingUser.setDob(userDto.getDob());
        existingUser.setGender(userDto.getGender());
        existingUser.setProfileImage(userDto.getProfileImage());
        existingUser.setJoiningDate(userDto.getJoiningDate());
        existingUser.setStatus(userDto.isStatus());
        existingUser.setUpdatedAt(LocalDateTime.now());

        User updatedUser = adminRepository.save(existingUser);

        return new APIResponse<>(
                true,
                "User updated successfully.",
                mapToResponse(updatedUser));
    }

    @Override
    public APIResponse<String> login(String email, String password) {
        Optional<User> optionalUser = adminRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return new APIResponse<>(
                    false,
                    "Invalid email",
                    null);
        }

        if (!passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            return new APIResponse<>(
                    false,
                    "Invalid Password", null);

        }

        String token = jwtService.generateToken(optionalUser.get().getId(),
                optionalUser.get().getEmail(), optionalUser.get().getRole().name());

        return new APIResponse<>(
                true,
                "Login Successfully",
                token);

    }
}
