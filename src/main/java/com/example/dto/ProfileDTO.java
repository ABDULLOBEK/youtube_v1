package com.example.dto;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    private Integer id;

    @NotBlank(message = "Name required")
    private String name;
    private String surname;

    @Email(message = "Email required")
    private String email;
    @NotBlank(message = "Password required")
    @NotNull(message = "Password is null!")
    private String password;

    @NotBlank(message = "Photo required")
    private AttachDTO photo;
    private String photoUrl;

    @NotNull(message = "Role is null!")
    private ProfileRole role;
    @NotNull(message = "Status is null!")
    private ProfileStatus status;
    private LocalDateTime createdDate;

//    jwt
    private String jwt;
}
