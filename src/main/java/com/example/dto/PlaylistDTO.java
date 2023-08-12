package com.example.dto;

import com.example.dto.base.IntegerBaseDTO;
import com.example.dto.base.StringBaseDTO;
import com.example.entity.ChannelEntity;
import com.example.enums.PlaylistStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistDTO extends IntegerBaseDTO {

    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNum;
    private String channelId;

}
