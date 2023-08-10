package com.example.repository;

import com.example.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {
//    findAllByPriceOrderByCreatedDate/**/
    Page<EmailHistoryEntity> findAllByToEmailOrderByCreatedDate(String price, Pageable pageable);

}
