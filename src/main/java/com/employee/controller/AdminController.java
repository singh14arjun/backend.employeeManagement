package com.employee.controller;

import com.employee.model.User;
import com.employee.payload.dto.UserRegisterDTO;
import com.employee.payload.dto.UserUpdateDTO;
import com.employee.payload.response.APIResponse;
import com.employee.payload.response.UserResponseDTO;
import com.employee.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/")
public class AdminController {

    private  final AdminService adminService;

    @GetMapping("/message")
    public  String message(){
        return "Hello World";
    }

    @PostMapping("register")
    public ResponseEntity<APIResponse<UserResponseDTO>> resigterUser(
            @Valid
            @RequestBody UserRegisterDTO userRegisterDTO){

        return ResponseEntity.ok(adminService.createUser(userRegisterDTO));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<APIResponse<UserResponseDTO>> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(adminService.getUserById(id));
    }
    @GetMapping("/users")
    public ResponseEntity<APIResponse<List<UserResponseDTO>>> getAllUser(){
        return ResponseEntity.ok(adminService.getAllUser());
    }

    @PutMapping("user/{id}")
    public ResponseEntity<APIResponse<UserResponseDTO>> updateUser(
            @PathVariable
            Long id,
            @RequestBody UserUpdateDTO userUpdateDTO
            ){
        return ResponseEntity.ok(adminService.updateUser(id,userUpdateDTO));
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<APIResponse<String>> deleteUserById(@PathVariable Long id) {

        APIResponse<String> response = adminService.deleteUser(id);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
