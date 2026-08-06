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
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    private String firstName;
    private String lastName;
    private Role role;
    private String email;
    private Department department;
    private double salary;
    private String phoneNumber;
    private Address address;
    private LocalDate dob;
    private String gender;
    private List<String> profileImage;
    private boolean status;
    private LocalDate joiningDate;

}
