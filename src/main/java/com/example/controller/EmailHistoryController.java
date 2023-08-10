package com.example.controller;

import com.example.dto.EmailHistoryDTO;
import com.example.dto.EmailHistoryFilterDTO;
import com.example.service.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/emailHistory")
public class EmailHistoryController {
    @Autowired
    private EmailHistoryService emailHistoryService;
    /*1. Get email pagination (ADMIN)
    2. Get Email pagination by email
    3. filter (email,created_date) + with pagination*/

    @GetMapping(value = "/pagination")
    public ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.ok(emailHistoryService.pagination(page - 1, size));
    }

    @GetMapping(value = "/paginationByEmail")
    public ResponseEntity<?> paginationByPrice(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size, String email) {
        return ResponseEntity.ok(emailHistoryService.paginationByEmail(page - 1, size, email));
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<List<EmailHistoryDTO>> filter(@RequestBody EmailHistoryFilterDTO filterDTO){
        List<EmailHistoryDTO> response = emailHistoryService.filter(filterDTO);
        return ResponseEntity.ok(response);
    }

}
