package com.example.controller;import com.example.dto.AttachDTO;import com.example.dto.JwtDTO;import com.example.dto.ProfileDTO;import com.example.service.ProfileService;import com.example.util.SecurityUtil;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PostAuthorize;import org.springframework.web.bind.annotation.*;@RestController@RequestMapping("/api/v1/profile")public class ProfileController {    @Autowired    private ProfileService profileService;    @PostAuthorize("hasAnyRole('ADMIN')")    @PostMapping(value = {"", "/"})    public ResponseEntity<?> create(@RequestBody ProfileDTO dto){        return ResponseEntity.ok(profileService.create(dto));    }    @PutMapping(value = "/{id}")    public ResponseEntity<?> updateDetail(@RequestBody ProfileDTO dto,                                          @RequestHeader("Authorization") String authToken){        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, null);        return ResponseEntity.ok(profileService.updateDetail(jwtDTO.getId(), dto));    }    @GetMapping(value = "/{id}")    public ResponseEntity<?> getDetail(@RequestHeader("Authorization") String authToken){        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, null);        return ResponseEntity.ok(profileService.getDetail(jwtDTO.getId()));    }    @PutMapping(value = "/{id}")    public ResponseEntity<?> updateAttach(@RequestBody AttachDTO dto,                                          @RequestHeader("Authorization") String authToken){        JwtDTO jwtDTO = SecurityUtil.hasRole(authToken, null);        return ResponseEntity.ok(profileService.updateAttach(jwtDTO.getId(), dto));    }}