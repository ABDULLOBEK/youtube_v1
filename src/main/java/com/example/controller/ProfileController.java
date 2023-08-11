package com.example.controller;import com.example.dto.AttachDTO;import com.example.dto.ProfileDTO;import com.example.service.ProfileService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PostAuthorize;import org.springframework.web.bind.annotation.*;@RestController@RequestMapping("/api/v1/profile")public class ProfileController {    @Autowired    private ProfileService profileService;    @PostAuthorize("hasAnyRole('ADMIN')")    @PostMapping("/admin/create")    public ResponseEntity<?> create(@RequestBody ProfileDTO dto) {        return ResponseEntity.ok(profileService.create(dto));    }    @PostAuthorize("hasAnyRole('ADMIN')")    @PutMapping(value = "/admin/update/detail/{profileId}")    public ResponseEntity<?> updateDetail(@PathVariable Integer profileId,                                          @RequestBody ProfileDTO dto) {        return ResponseEntity.ok(profileService.updateDetail(profileId, dto));    }    @PostAuthorize("hasRole('ADMIN')")    @GetMapping("/admin/get/{profileId}")    public ResponseEntity<?> getDetail(@PathVariable Integer profileId) {        return ResponseEntity.ok(profileService.getDetail(profileId));    }    @PostAuthorize("hasRole('ADMIN')")    @PutMapping("/admin/update-photo/{profileId}")    public ResponseEntity<?> updateAttach(@PathVariable Integer profileId,                                          @RequestBody AttachDTO dto) {        return ResponseEntity.ok(profileService.updateAttach(profileId, dto));    }}