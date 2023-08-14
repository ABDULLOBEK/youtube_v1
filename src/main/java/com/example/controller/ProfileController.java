package com.example.controller;import com.example.dto.ApiResponse;import com.example.dto.AttachDTO;import com.example.dto.ProfileDTO;import com.example.service.EmailSenderService;import com.example.service.ProfileService;import com.example.util.HTMLUtil;import jakarta.servlet.http.HttpServletResponse;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PostAuthorize;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.io.IOException;@RestController@RequestMapping("/api/v1/profile")public class ProfileController {    @Autowired    private ProfileService profileService;    @Autowired    private EmailSenderService emailSenderService;    @PreAuthorize("hasAnyRole('ADMIN')")    @PostMapping("/admin/create")    public ResponseEntity<ApiResponse> create(@RequestBody ProfileDTO dto) {        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.create(dto));    }    @PutMapping(value = "/open/update/detail/{profileId}")    public ResponseEntity<ApiResponse> updateDetail(@PathVariable Integer profileId,                                          @RequestBody ProfileDTO dto) {        return ResponseEntity.ok().body(profileService.updateDetail(profileId, dto));    }    @PutMapping(value = "/open/update/email/{email}")    public ResponseEntity<ApiResponse> updateEmail(@PathVariable String email) {        return ResponseEntity.ok().body(profileService.updateEmail(email));    }    @PutMapping("/open/update/password")    public ResponseEntity<ApiResponse> changePassword(@RequestParam("newPassword") String newPassword){        return ResponseEntity.ok().body(profileService.changePassword(newPassword));    }    @GetMapping("/get")    public ResponseEntity<ApiResponse> getDetail() {        return ResponseEntity.ok().body(profileService.getDetail());    }    @PutMapping("/open/update-photo/{profileId}")    public ResponseEntity<ApiResponse> updateAttach(@PathVariable Integer profileId,                                          @RequestBody AttachDTO dto) {        return ResponseEntity.ok().body(profileService.updateAttach(profileId, dto));    }}