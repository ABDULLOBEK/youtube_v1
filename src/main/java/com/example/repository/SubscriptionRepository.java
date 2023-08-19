package com.example.repository;

import com.example.entity.SubscriptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity, String>{

    Optional<SubscriptionEntity> findByProfileIdAndChannelId(Integer profileId, String channelId);

}
