package com.example.controller;import com.example.dto.ApiResponse;import com.example.dto.VideoDTO;import com.example.mapper.VideoFullInfoMapper;import com.example.mapper.VideoPlayListInfoMapper;import com.example.mapper.VideoShortInfoMapper;import com.example.service.VideoService;import io.swagger.v3.oas.annotations.Operation;import io.swagger.v3.oas.annotations.tags.Tag;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import javax.validation.Valid;@RestController@RequestMapping("/api/v1/video")@Tag(name = "VIDEO CONTROLLER 🎥", description = "this api used for video")public class VideoController {    @Autowired    private VideoService videoService;    //    @PreAuthorize("hasRole('USER')")    @Operation(summary = "create video ➕", description = "this api used for video creation")    @PostMapping("/open/create")    public ResponseEntity<ApiResponse> create(@Valid @RequestBody VideoDTO dto) {        return ResponseEntity                .status(HttpStatus.CREATED)                .body(videoService.create(dto));    }    @Operation(summary = "update video ➕", description = "this api used for video update details")    @PreAuthorize("hasAnyRole('USER', 'OWNER')")    @PostMapping("/close/update/{id}")    public ResponseEntity<ApiResponse> update(@PathVariable String id,                                              @Valid @RequestBody VideoDTO dto) {        return ResponseEntity.ok(videoService.update(id, dto));    }    @Operation(summary = "update video status ⭐", description = "this api used for video update status")    @PreAuthorize("hasAnyRole('USER', 'OWNER')")    @PostMapping("/close/update-status/{id}")    public ResponseEntity<ApiResponse> updateStatus(@PathVariable String id) {        return ResponseEntity.ok(videoService.updateStatus(id));    }    @Operation(summary = "increase video view count ➕➕", description = "this api used for video view count increase")    @PostMapping("/open/increase-view-count/{id}")    public ResponseEntity<ApiResponse> increaseViewCount(@PathVariable String id) {        return ResponseEntity.ok(videoService.increaseViewCount(id));    }    @Operation(summary = "paging video 📃", description = "this api used for paging video")    @GetMapping("/open/paging-by-category/{id}")    public ResponseEntity<Page<VideoDTO>> pagingByCategoryId(@PathVariable Integer id,                                                             @RequestParam(defaultValue = "0") Integer page,                                                             @RequestParam(defaultValue = "10") Integer size) {        Page<VideoDTO> videoDTOPage = videoService.pagingByCategoryId(id, page, size);        return ResponseEntity.ok(videoDTOPage);    }    @Operation(summary = "paging video by title 📃 ", description = "this api used for paging video by title")    @GetMapping("/open/paging/search-by-title/{title}")    public ResponseEntity<Page<VideoDTO>> searchVideoByTitle(@PathVariable String title,                                                             @RequestParam(defaultValue = "0") Integer page,                                                             @RequestParam(defaultValue = "10") Integer size) {        Page<VideoDTO> videoDTOPage = videoService.searchVideoByTitle(title, page, size);        return ResponseEntity.ok(videoDTOPage);    }    @Operation(summary = "paging video by tag 📃 ", description = "this api used for paging video by tag")    @GetMapping("/open/paging-by-tag/{id}")//GET /open/paging-by-tag?id=1&page=0&size=10    public ResponseEntity<Page<VideoDTO>> pagingByTagId(@PathVariable Integer id,                                                        @RequestParam(defaultValue = "0") Integer page,                                                        @RequestParam(defaultValue = "10") Integer size) {        Page<VideoDTO> videoDTOPage = videoService.pagingByTagId(id, page, size);        return ResponseEntity.ok(videoDTOPage);    }    @Operation(summary = "search video by id 🔍 ", description = "this api used for search video by id")    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")    @GetMapping("/close/paging/search-by-id/{id}")    public ResponseEntity<ApiResponse> searchVideoById(@PathVariable String id) {        return ResponseEntity.ok(videoService.searchVideoById(id));    }    @Operation(summary = "paging video by created-date 🔍 ", description = "this api used for paging video by created-date")    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/close/paging/by-created-date/descending")    public ResponseEntity<Page<VideoDTO>> pagingByCreatedDateDescending(@RequestParam(defaultValue = "0") Integer page,                                                                        @RequestParam(defaultValue = "30") Integer size) {        return ResponseEntity.ok(videoService.pagingByCreatedDateDescending(page, size));    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/close/paging/video-short-info")//GET /open/paging-by-tag?id=1&page=0&size=10    public ResponseEntity<Page<VideoShortInfoMapper>> pagingVideoShortInfo(@RequestParam(defaultValue = "0") Integer page,                                                                           @RequestParam(defaultValue = "30") Integer size) {        Page<VideoShortInfoMapper> videoShortInfoPage = videoService.pagingVideoShortInfo(page, size);        return ResponseEntity.ok(videoShortInfoPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/close/paging/video-full-info")    public ResponseEntity<Page<VideoFullInfoMapper>> pagingVideoFullInfo(@RequestParam(defaultValue = "0") Integer page,                                                                         @RequestParam(defaultValue = "30") Integer size) {        Page<VideoFullInfoMapper> videoFullInfoPage = videoService.pagingVideoFullInfo(page, size);        return ResponseEntity.ok(videoFullInfoPage);    }    @PreAuthorize("hasRole('ADMIN')")    @GetMapping("/close/paging/video-playlist-info")    public ResponseEntity<Page<VideoPlayListInfoMapper>> pagingVideoPlaylistInfo(@RequestParam(defaultValue = "0") Integer page,                                                                                 @RequestParam(defaultValue = "30") Integer size) {        Page<VideoPlayListInfoMapper> videoPlayListInfoMapper = videoService.pagingVideoPlaylistInfo(page, size);        return ResponseEntity.ok(videoPlayListInfoMapper);    }}