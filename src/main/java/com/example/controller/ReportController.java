package com.example.controller;

import com.example.dto.ApiResponse;
import com.example.dto.ReportDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/report")
@Tag(name = "TAG CONTROLLER #️⃣", description = "this api used for tag")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Operation(summary = "create tag ➕", description = "this api used for tag creation")
    @PostMapping("/close/ceate")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody ReportDTO dto) {
        ApiResponse response = reportService.create(dto);
        if (response.getStatus()) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
    }
    @Operation(summary = "paging tags 📄#️⃣", description = "this api used for paging tags")
    @GetMapping("/close/paging")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<ReportDTO>> paging(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "30") Integer size) {
        Page<ReportDTO> reportDTOPage = reportService.paging(page, size);
        return ResponseEntity.ok(reportDTOPage);
    }
    @Operation(summary = "remove tag ❌", description = "this api used for remove tag")
    @DeleteMapping("/close/delete/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ApiResponse> remove(@PathVariable String id) {
        ApiResponse response = reportService.remove(id);
        if (response.getStatus()) {
            return ResponseEntity
//                    .status(HttpStatus.NO_CONTENT)
//                    .body(response);
                    .ok(response);
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }
    }
}
