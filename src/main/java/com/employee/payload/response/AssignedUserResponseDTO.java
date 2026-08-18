package com.employee.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedUserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<String> profileImage;
}
