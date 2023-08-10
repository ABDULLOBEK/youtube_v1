package com.example.dto;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private Integer id;
    private String email;

    public JwtDTO(Integer id) {
        this.id = id;
    }

    public JwtDTO(String email) {
        this.email = email;
    }

}
