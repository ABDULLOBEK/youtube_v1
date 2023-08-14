package com.example.controller;import com.example.dto.ApiResponse;import com.example.dto.VideoDTO;import com.example.service.VideoService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;import java.util.List;@RestController@RequestMapping("/api/v1/video")public class VideoController {    @Autowired    private VideoService videoService;    //    @PreAuthorize("hasRole('USER')")    @PostMapping("/open/create")    public ResponseEntity<ApiResponse> create(@Valid @RequestBody VideoDTO dto) {        return ResponseEntity                .status(HttpStatus.CREATED)                .body(videoService.create(dto));    }    @PreAuthorize("hasAnyRole('USER', 'OWNER')")    @PostMapping("/close/update/{id}")    public ResponseEntity<ApiResponse> update(@PathVariable String id,                                              @Valid @RequestBody VideoDTO dto) {        return ResponseEntity.ok(videoService.update(id, dto));    }    @PreAuthorize("hasAnyRole('USER', 'OWNER')")    @PostMapping("/close/update-status/{id}")    public ResponseEntity<ApiResponse> updateStatus(@PathVariable String id) {        return ResponseEntity.ok(videoService.updateStatus(id));    }    @PostMapping("/open/increase-view-count/{id}")    public ResponseEntity<ApiResponse> increaseViewCount(@PathVariable String id) {        return ResponseEntity.ok(videoService.increaseViewCount(id));    }    @GetMapping("/open/paging-by-category/{id}")    public ResponseEntity<Page<VideoDTO>> pagingByCategoryId(@PathVariable Integer id,                                                             @RequestParam(defaultValue = "0") Integer page,                                                             @RequestParam(defaultValue = "10") Integer size) {        Page<VideoDTO> videoDTOPage = videoService.pagingByCategoryId(id, page, size);        return ResponseEntity.ok(videoDTOPage);    }    @GetMapping("/open/paging/search-by-title/{title}")    public ResponseEntity<Page<VideoDTO>> searchVideoByTitle(@PathVariable String title,                                                             @RequestParam(defaultValue = "0") Integer page,                                                             @RequestParam(defaultValue = "10") Integer size) {        Page<VideoDTO> videoDTOPage = videoService.searchVideoByTitle(title, page, size);        return ResponseEntity.ok(videoDTOPage);    }    @GetMapping("/open/paging-by-tagId/{id}")//GET /open/paging-by-tag?id=1&page=0&size=10    public ResponseEntity<Page<VideoDTO>> pagingByTagId(@PathVariable Integer id,                                                        @RequestParam(defaultValue = "0") Integer page,                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<VideoDTO> videoDTOPage = videoService.pagingByTagId(id, page, size);        return ResponseEntity.ok(videoDTOPage);    }//    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")//    @GetMapping("/close/paging/search-by-title/{title}")//    public ResponseEntity<Page<VideoDTO>> searchVideoByTitle(@PathVariable String title,//                                                             @RequestParam(defaultValue = "0") Integer page,//                                                             @RequestParam(defaultValue = "10") Integer size) {//        Page<VideoDTO> videoDTOPage = videoService.searchVideoByTitle(title, page, size);//        return ResponseEntity.ok(videoDTOPage);}}