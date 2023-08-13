package com.example.repository;import com.example.entity.VideoEntity;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.repository.JpaRepository;import java.util.List;public interface VideoRepository extends JpaRepository<VideoEntity, String> {    Page<VideoEntity> findAllByCategoryId(Integer categoryId, Pageable pageable);    Page<VideoEntity> findAllByTitle(String title, Pageable pageable);//    Page<VideoEntity> findAllByTagId(List<Integer> tagId, Pageable pageable);}