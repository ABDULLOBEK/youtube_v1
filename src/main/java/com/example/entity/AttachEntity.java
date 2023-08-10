package com.example.entity;

import com.example.entity.Base.StringBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attach")
@Getter
@Setter
public class AttachEntity extends StringBaseEntity {

    @Column(name = "origin_name")
    private String originName;

    @Column(name = "size")
    private Long size;

    @Column(name = "path")
    private String path;

    @Column(name = "extension")
    private String extension;

    @Column(name = "duration")
    private String duration;





}
