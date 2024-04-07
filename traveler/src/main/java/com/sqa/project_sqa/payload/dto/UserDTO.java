package com.sqa.project_sqa.payload.dto;


import com.sqa.project_sqa.entities.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;

    private String name;

    private String userName;

    private String email;

    private String phone;

    private ERole role ;
}
