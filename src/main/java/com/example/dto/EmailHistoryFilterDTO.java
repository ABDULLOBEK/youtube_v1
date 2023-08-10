package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class EmailHistoryFilterDTO {
    private Integer id;
    private String toEmail;
    private String title;
    private String message;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
}
