package com.example.service;

import com.example.dto.ChannelDTO;
import com.example.entity.ChannelEntity;
import com.example.exp.AppMethodNotAllowedException;
import com.example.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public ChannelDTO add(ChannelDTO dto) {

        Optional<ChannelEntity> optional = channelRepository.findByName(dto.getName());
        System.out.println("111111111111111111");
        if (!optional.isPresent()){
            System.out.println("22222222");
            throw new AppMethodNotAllowedException();
        }

        System.out.println("333333333333333333");
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPhotoId(dto.getPhotoId());
        System.out.println("44444444444444444444");
        entity.setBannerId(dto.getBannerId());
       /* CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();
        entity.setProfileId(customUserDetails.getProfile().getId());*/
        System.out.println(entity);
        channelRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
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

    public boolean update(Integer id, ChannelDTO dto) {
        int affectedRows = channelRepository.update(id, dto.getName(), dto.getDescription());
        return affectedRows==1;
    }

    public Boolean PhotoUP(Integer id, ChannelDTO dto) {
        int affectedRows = channelRepository.updatePhoto(id, dto.getPhotoId());
        return affectedRows==1;
    }

    public Boolean bannerId(Integer id, ChannelDTO dto) {
        int affectedRows = channelRepository.updateBanner(id, dto.getBannerId());
        return affectedRows==1;
    }
}
