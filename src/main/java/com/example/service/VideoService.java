package com.example.service;import com.example.config.CustomUserDetails;import com.example.dto.ApiResponse;import com.example.dto.VideoDTO;import com.example.entity.VideoEntity;import com.example.entity.VideoTagEntity;import com.example.entity.VideoWatched;import com.example.enums.VideoStatus;import com.example.mapper.VideoFullInfoMapper;import com.example.mapper.VideoPlayListInfoMapper;import com.example.mapper.VideoShortInfoMapper;import com.example.repository.VideoRepository;import com.example.repository.VideoWatchedRepository;import com.example.util.SpringSecurityUtil;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.context.support.ResourceBundleMessageSource;import org.springframework.data.domain.*;import org.springframework.stereotype.Service;import java.util.List;import java.util.Optional;import java.util.ResourceBundle;@Servicepublic class VideoService {    @Autowired    private VideoRepository videoRepository;    @Autowired    private VideoWatchedRepository videoWatchedRepository;    private final Logger log = LoggerFactory.getLogger(VideoService.class);    private VideoEntity TO_ENTITY(VideoDTO dto) {        VideoEntity entity = new VideoEntity();        entity.setPreviewAttachId(dto.getPreviewAttachId());        entity.setTitle(dto.getTitle());        entity.setCategoryId(dto.getCategoryId());        entity.setAttachId(dto.getAttachId());        entity.setChannelId(dto.getChannelId());        entity.setVideoType(dto.getVideoType());        entity.setVideoStatus(dto.getVideoStatus());        entity.setDescription(dto.getDescription());        entity.setPrtId(dto.getPrtId());        //all counts are default 0        return entity;    }    private VideoDTO TO_DTO(VideoEntity entity) {        VideoDTO dto = new VideoDTO();        dto.setId(entity.getId());        dto.setVisible(entity.getVisible());        dto.setCreatedDate(entity.getCreatedDate());        dto.setPreviewAttachId(entity.getPreviewAttachId());        dto.setTitle(entity.getTitle());        dto.setCategoryId(entity.getCategoryId());        dto.setAttachId(entity.getAttachId());        dto.setChannelId(entity.getChannelId());        dto.setVideoType(entity.getVideoType());        dto.setVideoStatus(entity.getVideoStatus());        dto.setDescription(entity.getDescription());        return dto;    }    public ApiResponse create(VideoDTO dto) {        VideoEntity entity = TO_ENTITY(dto);        VideoEntity created = videoRepository.save(entity);        log.info("created video id: " + created.getId() + " title: " + created.getTitle());        return new ApiResponse(true, TO_DTO(entity));    }    public ApiResponse update(String id, VideoDTO dto) {        Optional<VideoEntity> optionalVideo = videoRepository.findById(id);        if (optionalVideo.isPresent()) {            VideoEntity entity = optionalVideo.get();//            entity.setPreviewAttachId(dto.getPreviewAttachId()); //TODO mine            entity.setTitle(dto.getTitle());            entity.setCategoryId(dto.getCategoryId());//            entity.setAttachId(dto.getAttachId());//            entity.setChannelId(dto.getChannelId());            entity.setVideoType(dto.getVideoType());            entity.setVideoStatus(dto.getVideoStatus());            entity.setDescription(dto.getDescription());            log.warn("updated video details id: " + entity.getId() + " title: " + entity.getTitle());            VideoEntity updated = videoRepository.save(entity);            return new ApiResponse(true, TO_DTO(updated));        }        return new ApiResponse(false, "UPDATE DETAILS FAILED!!!");    }    public ApiResponse updateStatus(String id) {        Optional<VideoEntity> optionalVideo = videoRepository.findById(id);        if (optionalVideo.isPresent()) {            VideoEntity entity = optionalVideo.get();            if (entity.getVideoStatus().equals(VideoStatus.PUBLIC)) {                entity.setVideoStatus(VideoStatus.PRIVATE);            } else {                entity.setVideoStatus(VideoStatus.PUBLIC);            }            VideoEntity updated = videoRepository.save(entity);            log.warn("updated video status id: " + entity.getId() + " title: " + entity.getTitle() + " status" + entity.getVideoStatus());            return new ApiResponse(true, TO_DTO(updated));        }        return new ApiResponse(false, "UPDATE STATUS FAILED!!!");    }    public ApiResponse increaseViewCount(String id) {        Optional<VideoEntity> optionalVideo = videoRepository.findById(id);        CustomUserDetails customUserDetails = SpringSecurityUtil.getCurrentUser();        if (optionalVideo.isPresent()) {            VideoWatched videoWatched = new VideoWatched();            videoWatched.setVideoId(optionalVideo.get().getId());            videoWatched.setProfileId(customUserDetails.getProfile().getId());            videoWatchedRepository.save(videoWatched);//TODO trigger worked after insert video_watched            return new ApiResponse(true, videoWatched);        }        return new ApiResponse(false, "UPDATE VIEW COUNT FAILED!!!");    }    public Page<VideoDTO> pagingByCategoryId(Integer categoryId, Integer page, Integer size) {        Sort sort = Sort.by("viewCount");        Pageable pageable = PageRequest.of(page, size, sort);        Page<VideoEntity> videoEntityPage = videoRepository                .findAllByCategoryId(categoryId, pageable);        List<VideoDTO> videoDTOList = videoEntityPage                .stream()                .map(this::TO_DTO)                .toList();        return new PageImpl<>(videoDTOList, pageable, videoEntityPage.getTotalPages());    }    public Page<VideoDTO> searchVideoByTitle(String title, Integer page, Integer size) {        Sort sort = Sort.by("viewCount");        Pageable pageable = PageRequest.of(page, size, sort);        Page<VideoEntity> videoEntityPage = videoRepository                .findAllByTitle(title, pageable);        List<VideoDTO> videoDTOList = videoEntityPage                .stream()                .map(this::TO_DTO)                .toList();        return new PageImpl<>(videoDTOList, pageable, videoEntityPage.getTotalPages());    }    public Page<VideoDTO> pagingByTagId(Integer id, Integer page, Integer size) {        Sort sort = Sort.by("viewCount");        Pageable pageable = PageRequest.of(page, size, sort);        Page<VideoEntity> videoEntityPage = videoRepository                .findAllByTagId(id, pageable);        List<VideoDTO> videoDTOList = videoEntityPage                .stream()                .map(this::TO_DTO)                .toList();        return new PageImpl<>(videoDTOList, pageable, videoEntityPage.getTotalPages());    }    public ApiResponse searchVideoById(String id) {        Integer prtId = SpringSecurityUtil.getCurrentProfileId();        Optional<VideoEntity> optionalVideo = videoRepository.findById(id);        if (optionalVideo.isPresent()) {            VideoEntity videoEntity = optionalVideo.get();            if (videoEntity.getPrtId().equals(prtId)) {                return new ApiResponse(true, videoEntity); // FULL INFO            } else {                return new ApiResponse(false, "THIS VIDEO NOT ACCESS FOR YOU!!!");            }        }        return new ApiResponse(false, "VIDEO NOT NOT FOUND!!!");    }    public Page<VideoDTO> pagingByCreatedDateDescending(Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<VideoEntity> videoEntityPage = videoRepository                .findAllByCreatedDate(pageable);        List<VideoDTO> videoProfileDTOList = videoEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging video by created date");        return new PageImpl<>(videoProfileDTOList, pageable, videoEntityPage.getTotalPages());    }    public Page<VideoShortInfoMapper> pagingVideoShortInfo(Integer page, Integer size) {        Sort sort = Sort.by("created_date").descending();        Pageable pageable = PageRequest.of(page, size, sort);        log.info("paging video short info");        return videoRepository                .pagingVideoShortInfo(pageable);    }    public Page<VideoFullInfoMapper> pagingVideoFullInfo(Integer page, Integer size) {        Sort sort = Sort.by("created_date").descending();        Pageable pageable = PageRequest.of(page, size, sort);        log.info("paging video full info");        return videoRepository                .pagingVideoFullInfo(pageable);    }    public Page<VideoPlayListInfoMapper> pagingVideoPlaylistInfo(Integer page, Integer size) {        Sort sort = Sort.by("created_date").ascending();        Pageable pageable = PageRequest.of(page, size, sort);        log.info("paging video playlist info");        return videoRepository                .pagingVideoPlaylistInfo(pageable);    }}