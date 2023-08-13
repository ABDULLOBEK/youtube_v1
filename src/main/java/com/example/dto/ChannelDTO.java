package com.example.dto;

import com.example.enums.ChannelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {

    private String id;

    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Photo required")
    private String photoId;

    @NotBlank(message = "Description required")
    private String description;

    @NotNull(message = "Status is null!")
    private ChannelStatus status;

    @NotBlank(message = "Photo required")
    private String bannerId;

    @NotBlank(message = "Photo profile_id")
    private String profile_id;
}
