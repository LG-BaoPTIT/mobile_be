package com.sqa.project_sqa.payload.response;

import com.sqa.project_sqa.payload.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String jwtToken;
    private UserDTO userDTO;

}
