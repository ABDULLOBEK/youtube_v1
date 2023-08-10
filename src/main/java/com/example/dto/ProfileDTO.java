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
    private String password;

//    @NotBlank(message = "Photo required")
    private AttachDTO photo;
    private Integer photoId;

    @NotNull
    private ProfileRole role;
    @NotNull
    private ProfileStatus status;
    private LocalDateTime createdDate;
}
