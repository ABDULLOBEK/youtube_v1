package com.example.entity;
import com.example.entity.Base.IntegerBaseEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class ChannelEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_id")
    private String photoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ChannelStatus status;

    @Column(name = "bannerId")
    private String bannerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bannerId", insertable = false, updatable = false)
    private AttachEntity banner;

    @Column(name = "profile_id")
    private Integer profileId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false, updatable = false)
    private ProfileEntity profile;
}
