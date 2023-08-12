package com.example.service;

import com.example.dto.EmailHistoryDTO;
import com.example.dto.PlaylistDTO;
import com.example.dto.TagDTO;
import com.example.entity.EmailHistoryEntity;
import com.example.entity.PlaylistEntity;
import com.example.entity.TagEntity;
import com.example.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;

    public PlaylistDTO add(PlaylistDTO dto) {
        PlaylistEntity entity = toEntity(dto);
        playlistRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public Boolean update(Integer id, PlaylistDTO dto){
        int affectedRows = playlistRepository.update(id, dto.getName());
        return affectedRows==1;
    }

    public String delete(Integer id) {
        playlistRepository.deleteById(id);
        return "Category Deleted";
    }

    public PageImpl<PlaylistDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC, "id");
        Page<PlaylistEntity> pageObj = playlistRepository.findAll(pageable);
        List<PlaylistDTO> playlistDTOList = pageObj.stream().map(this::toDTO).collect(Collectors.toList());
        return new PageImpl<>(playlistDTOList, pageable, pageObj.getTotalElements());
    }

    private PlaylistDTO toDTO(PlaylistEntity entity) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.setName(entity.getName());
        dto.setVisible(entity.getVisible());
        dto.setDescription(entity.getDescription());
        dto.setChannelId(entity.getChannelId());
        dto.setStatus(entity.getStatus());
        dto.setOrderNum(entity.getOrderNum());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


    private PlaylistEntity toEntity(PlaylistDTO dto) {
        PlaylistEntity entity = new PlaylistEntity();
        entity.setName(dto.getName());
        entity.setVisible(dto.getVisible());
        entity.setDescription(dto.getDescription());
        entity.setChannelId(dto.getChannelId());
        entity.setStatus(dto.getStatus());
        entity.setOrderNum(dto.getOrderNum());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

}
