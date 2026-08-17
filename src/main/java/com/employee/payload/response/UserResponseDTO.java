package com.employee.payload.response;

import com.employee.constant.Department;
import com.employee.constant.Role;
import com.employee.model.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private Department department;
    private double salary;
    private String email;
    private String password;
    private List<String> profileImage;
    private String phoneNumber;
    private Address address;
    private LocalDate dob;
    private String gender;
    private String jobTitle;
    private LocalDate joiningDate;
    private boolean status;
}
