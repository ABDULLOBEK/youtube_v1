package com.example.controller;

import com.example.dto.ApiResponse;
import com.example.dto.ProfileDTO;
import com.example.dto.SubscriptionDTO;
import com.example.enums.SubscriptionStatus;
import com.example.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@RequestBody SubscriptionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.create(dto));
    }

    @PutMapping(value = "/update/status/{id}")
    public ResponseEntity<ApiResponse> changeStatus(@PathVariable String id,
                                                    @RequestParam SubscriptionStatus status){
        return ResponseEntity.ok().body(subscriptionService.changeStatus(id, status));
    }

}
