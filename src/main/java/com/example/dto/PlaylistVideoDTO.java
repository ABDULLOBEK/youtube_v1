package com.example.dto;import com.example.entity.Base.IntegerBaseEntity;import com.example.entity.PlaylistEntity;import com.example.entity.VideoEntity;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.Getter;import lombok.Setter;import javax.validation.constraints.NotNull;@Getter@Setter@JsonInclude(JsonInclude.Include.NON_NULL)public class PlaylistVideoDTO extends IntegerBaseEntity {    @NotNull(message = "PLAYLIST ID SHOULD NOT BE NULL!!!")    private Integer playlistId;    private PlaylistEntity playlistEntity;    @NotNull(message = "VIDEO ID SHOULD NOT BE NULL!!!")    private String videoId;    private VideoEntity videoEntity;    private Integer orderNum;    private Integer prtId;}