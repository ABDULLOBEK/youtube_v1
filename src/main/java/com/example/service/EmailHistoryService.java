package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.EmailHistoryDTO;
import com.example.dto.EmailHistoryFilterDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.EmailHistoryEntity;
import com.example.exp.ItemNotFoundException;
import com.example.repository.CustomEmailHistoryRepository;
import com.example.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailHistoryService {

    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    @Autowired
    private CustomEmailHistoryRepository customEmailHistoryRepository;

    public PageImpl<EmailHistoryDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC, "id");
        Page<EmailHistoryEntity> pageObj = emailHistoryRepository.findAll(pageable);
        List<EmailHistoryDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());
    }

    public PageImpl<EmailHistoryDTO> paginationByEmail(int page, int size, String  email){
        Pageable pageable = PageRequest.of(page,size);
        Page<EmailHistoryEntity> pageObj = emailHistoryRepository.findAllByToEmailOrderByCreatedDate(email,pageable);
        List<EmailHistoryDTO> studentDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(studentDTOList, pageable, pageObj.getTotalElements());
    }

    public List<EmailHistoryDTO> filter(EmailHistoryFilterDTO filterDTO){
        if(filterDTO.getCreatedDateFrom()!=null || filterDTO.getCreatedDateTo()!=null) {
            LocalDateTime from = LocalDateTime.of(filterDTO.getCreatedDateFrom().toLocalDate(), LocalTime.MIN);
            LocalDateTime to = LocalDateTime.of(filterDTO.getCreatedDateTo().toLocalDate(), LocalTime.MAX);
            filterDTO.setCreatedDateTo(to);
            filterDTO.setCreatedDateFrom(from);
        }
        return getCourseDTOS(customEmailHistoryRepository.filter(filterDTO));
    }

    private EmailHistoryDTO toDTO(EmailHistoryEntity entity) {
        EmailHistoryDTO dto = new EmailHistoryDTO();
        dto.setToEmail(entity.getToEmail());
        dto.setTitle(entity.getTitle());
        dto.setMessage(entity.getMessage());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private List<EmailHistoryDTO> getCourseDTOS(List<EmailHistoryEntity> entityList) {
        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        if (entityList.isEmpty()) {
            throw new ItemNotFoundException("Email History not found");
        }
        for (EmailHistoryEntity s:entityList){
            dtoList.add(toDTO(s));
        }
        return dtoList;
    }


}