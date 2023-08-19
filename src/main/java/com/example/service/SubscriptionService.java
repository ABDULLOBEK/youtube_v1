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

        SubscriptionEntity entity1 = getSubscription(customUserDetails.getProfile().getId(), dto.getChannelId());

        if (entity1 != null){
            return new ApiResponse(false, "YOU HAVE ALREADY SUBSCRIPT TO THIS CHANNEL");
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
    private SubscriptionEntity getSubscription(Integer profileId, String channelId) {
        Optional<SubscriptionEntity> optional = subscriptionRepository.findByProfileIdAndChannelId(profileId, channelId);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public ApiResponse changeStatus(String channelId, SubscriptionStatus status) {
        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();

        SubscriptionEntity entity = getSubscription(customUserDetails.getProfile().getId(), channelId);
        if (entity == null){
            return new ApiResponse(false, "THIS PROFILE IS NOT FOUND");
        }
        entity.setStatus(status);
        subscriptionRepository.save(entity);

        return new ApiResponse(true, "YOUR STATUS UPDATED SUCCESSFULLY");
    }
}
