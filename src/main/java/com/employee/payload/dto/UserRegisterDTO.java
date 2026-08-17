package com.employee.payload.dto;

import com.employee.constant.Department;
import com.employee.constant.Role;
import com.employee.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserRegisterDTO {

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
    private String jobTitle;

    private String gender;
    private LocalDate joiningDate;


}
