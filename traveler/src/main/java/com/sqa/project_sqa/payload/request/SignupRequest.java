package com.sqa.project_sqa.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SignupRequest {
    private String name;

    private String userName;

    private String password;

    private String email;

    private String phone;
}
