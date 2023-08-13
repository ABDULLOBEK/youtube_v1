package com.example.controller;


import com.example.dto.ChannelDTO;
import com.example.dto.PlaylistDTO;
import com.example.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ChannelDTO dto){
            return ResponseEntity.ok(channelService.add(dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ChannelDTO dto,
                                    @PathVariable("id") Integer id){
        Boolean response =channelService.update(id,dto);
        return ResponseEntity.ok( response);
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_USER')")
    @PutMapping("/update/photo/{id}")
    public ResponseEntity<?> photoUp(@RequestBody ChannelDTO dto, @PathVariable("id") Integer id){
        Boolean response =channelService.PhotoUP(id,dto);
        return ResponseEntity.ok( response);
    }

    @PreAuthorize("hasAnyRole('ROLE_OWNER','ROLE_USER')")
    @PutMapping("/update/banner/{id}")
    public ResponseEntity<?> bannerUp(@RequestBody ChannelDTO dto, @PathVariable("id") Integer id){
        Boolean response =channelService.bannerId(id,dto);
        return ResponseEntity.ok( response);
    }
}
