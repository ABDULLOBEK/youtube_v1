package com.example.mapper;import com.fasterxml.jackson.annotation.JsonInclude;import lombok.AllArgsConstructor;import lombok.Getter;import lombok.Setter;import java.time.LocalDateTime;@Setter@Getter@AllArgsConstructor@JsonInclude(JsonInclude.Include.NON_NULL)public class VideoPlayListInfoMapper {    private String id;    private String title;    private String previewAttachId;    private String previewAttachUrl;    private Long viewCount;    private LocalDateTime publishedDate;    private String duration;}