package com.example.entity;

import com.example.entity.Base.StringBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "attach")
@Getter
@Setter
public class AttachEntity extends StringBaseEntity {
}
