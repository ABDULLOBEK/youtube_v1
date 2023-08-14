package com.example.service;

import com.example.config.CustomUserDetails;
import com.example.dto.ApiResponse;
import com.example.dto.ChannelDTO;
import com.example.entity.ChannelEntity;
import com.example.entity.PlaylistEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ChannelStatus;
import com.example.exp.AppMethodNotAllowedException;
import com.example.repository.ChannelRepository;
import com.example.util.SpringSecurityUtil;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public ChannelDTO add(ChannelDTO dto) {
        Optional<ChannelEntity> optionalProfile = channelRepository.findByName(dto.getName());
        if (optionalProfile.isPresent()) {
//       int channelEntity = channelRepository.findByName(dto.getName());
//        if (channelEntity!=1) {
            throw new AppMethodNotAllowedException();
        }

        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPhotoId(dto.getPhotoId());
        entity.setBannerId(dto.getBannerId());
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        entity.setProfileId(customUserDetails.getProfile().getId());
        System.out.println(entity.getProfileId());
        channelRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    public boolean update(String id, ChannelDTO dto) {
        int affectedRows = channelRepository.update(id, dto.getName(), dto.getDescription());
        return affectedRows == 1;
    }

    public Boolean PhotoUP(String id, ChannelDTO dto) {
        int affectedRows = channelRepository.updatePhoto(id, dto.getPhotoId());
        return affectedRows == 1;
    }

    public Boolean bannerId(String id, ChannelDTO dto) {
        int affectedRows = channelRepository.updateBanner(id, dto.getBannerId());
        return affectedRows == 1;
    }


    public PageImpl<ChannelDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChannelEntity> pageObj = channelRepository.findAllBy(pageable);

        List<ChannelEntity> entityList = pageObj.getContent();
        Long totalCount = pageObj.getTotalElements();

        List<ChannelDTO> channelDTOList = new LinkedList<>();
        entityList.forEach(entity -> {
            channelDTOList.add(toDTO(entity));
        });

        PageImpl<ChannelDTO> pageImpl = new PageImpl<ChannelDTO>(channelDTOList, pageable, totalCount);
        return pageImpl;
    }


    private ChannelDTO toDTO(ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    private ChannelEntity toEntity(ChannelDTO dto) {
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public ChannelDTO search(String id) {
        Optional<ChannelEntity> entity = channelRepository.findById(id);
        if (entity.isPresent()) {
//            throw new AppBadRequestException("Email already exists");
            throw new AppMethodNotAllowedException();
        }
        return toDTO(entity);
    }

    private ChannelDTO toDTO(Optional<ChannelEntity> entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setName(entity.get().getName());
        dto.setDescription(entity.get().getDescription());
        dto.setStatus(entity.get().getStatus());
        dto.setPhotoId(entity.get().getPhotoId());
        dto.setBannerId(entity.get().getBannerId());
        return dto;
    }

    public Boolean statusUpdate(String id,ChannelDTO dto) {
//        @NotNull(message = "Status is null!") ChannelStatus status = dto.getStatus();
//        int affectedRows = channelRepository.updateStatus(id,status);
//        return affectedRows == 1;
        Optional<ChannelEntity> entity = channelRepository.findById(id);
        if(entity.isPresent()){
            if (entity.get().getStatus().equals(dto.getStatus())){
                return false;
            }else {
                entity.get().setStatus(dto.getStatus());
            }
        }
        channelRepository.save(entity.get());
        return true;
    }

    public List<ChannelDTO> all() {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        Integer id = customUserDetails.getProfile().getId();
        Iterable<ChannelEntity> iterable = channelRepository.findAllByProfileId(id);
        List<ChannelDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            dtoList.add(toDTO(entity));
        });
        return dtoList;
    }
}