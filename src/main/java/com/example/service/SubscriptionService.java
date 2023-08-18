package com.example.service;

import com.example.config.CustomUserDetails;
import com.example.dto.ApiResponse;
import com.example.dto.SubscriptionDTO;
import com.example.entity.SubscriptionEntity;
import com.example.enums.SubscriptionStatus;
import com.example.repository.SubscriptionRepository;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public ApiResponse create(SubscriptionDTO dto) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        ApiResponse response = checkSubscription(customUserDetails.getProfile().getId(), dto.getChannelId());

        if (response != null){
            return response;
        }

        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setProfileId(customUserDetails.getProfile().getId());
        entity.setChannelId(dto.getChannelId());
        entity.setStatus(SubscriptionStatus.ACTIVE);
        entity.setNotificationType(dto.getNotificationType());
        subscriptionRepository.save(entity);

        dto.setId(entity.getId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        return new ApiResponse(true, dto);
    }

    private ApiResponse checkSubscription(Integer profileId, String channelId) {
        Optional<SubscriptionEntity> optional = subscriptionRepository.findByProfileId(profileId);
        if (optional.isPresent()){
            SubscriptionEntity entity = optional.get();
            if (entity.getStatus() == SubscriptionStatus.BLOCK){
                return new ApiResponse(false, "YOU ARE BLOCKED FROM THIS CHANNEL");
            }
            return new ApiResponse(false, "YOU ARE ALREADY SUBSCRIPT TO THIS CHANNEL");
        }
        return null;
    }
}
