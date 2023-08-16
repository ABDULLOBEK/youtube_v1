package com.example.mapper;import com.example.enums.VideoStatus;import com.example.enums.VideoType;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.AllArgsConstructor;import lombok.Getter;import lombok.Setter;import java.time.LocalDateTime;@Getter@Setter@AllArgsConstructor@JsonInclude(JsonInclude.Include.NON_NULL)public class VideoShortInfoMapper {    private String id;    private String title;    private String category;    private String attachId;    private String channelId;    private String description;    private VideoType videoType;    private VideoStatus videoStatus;    private LocalDateTime createdDate;    private Integer prtId;    private String name;    private String surname;}