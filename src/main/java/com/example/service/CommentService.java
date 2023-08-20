package com.example.service;import com.example.config.CustomUserDetails;import com.example.dto.ApiResponse;import com.example.dto.CommentDTO;import com.example.entity.CommentEntity;import com.example.enums.ProfileRole;import com.example.repository.CommentRepository;import com.example.util.SpringSecurityUtil;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.*;import org.springframework.stereotype.Service;import java.time.LocalDate;import java.time.LocalDateTime;import java.util.List;import java.util.Optional;@Servicepublic class CommentService {    @Autowired    private CommentRepository commentRepository;    @Autowired    private ResourceBundleService resourceBundleService;    private final Logger log = LoggerFactory.getLogger(CommentService.class);    private CommentEntity TO_ENTITY(CommentDTO dto) {        CommentEntity entity = new CommentEntity();        entity.setProfileId(dto.getProfileId());        entity.setVideoId(dto.getVideoId());        entity.setContent(dto.getContent());        entity.setReplyId(dto.getReplyId());        return entity;    }    private CommentDTO TO_DTO(CommentEntity entity) {        CommentDTO dto = new CommentDTO();        dto.setProfileId(entity.getProfileId());        dto.setContent(entity.getContent());        dto.setReplyId(entity.getReplyId());        dto.setLikeCount(entity.getLikeCount());        dto.setDislikeCount(entity.getDislikeCount());        dto.setCreatedDate(entity.getCreatedDate());//        dto.setVideoId(entity.getVideoId());//        dto.setId(entity.getId());//        dto.setVisible(entity.getVisible());        return dto;    }    public ApiResponse create(CommentDTO dto) {        dto.setProfileId(SpringSecurityUtil.getCurrentProfileId());// set prtId        CommentEntity entity = TO_ENTITY(dto);        CommentEntity created = commentRepository.save(entity);        return new ApiResponse(true, TO_DTO(created));    }    public ApiResponse update(String id, String newContent) {        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();        Optional<CommentEntity> optionalComment = commentRepository.findById(id);        if (optionalComment.isPresent()) {            CommentEntity entity = optionalComment.get();            if (entity.getProfileId().equals(currentUser.getProfile().getId()) ||                    currentUser.getProfile().getRole().equals(ProfileRole.ROLE_ADMIN)) {                entity.setContent(newContent);                CommentEntity updated = commentRepository.save(entity);                return new ApiResponse(true, TO_DTO(updated));            } else {                return new ApiResponse(false, resourceBundleService.getMessage("you.are.not.allowed", currentUser.getProfile().getLanguage()));            }        }        return new ApiResponse(false, resourceBundleService.getMessage("item.not.found", currentUser.getProfile().getLanguage()));    }    public ApiResponse delete(String id) {        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();        Optional<CommentEntity> optionalComment = commentRepository.findById(id);        if (optionalComment.isPresent()) {            CommentEntity entity = optionalComment.get();            if (entity.getProfileId().equals(currentUser.getProfile().getId()) ||                    currentUser.getProfile().getRole().equals(ProfileRole.ROLE_ADMIN)) {                commentRepository.deleteById(id);                return new ApiResponse(true, resourceBundleService.getMessage("success.deleted", currentUser.getProfile().getLanguage()) + "COMMENT id: " + id);            } else {                return new ApiResponse(false, resourceBundleService.getMessage("you.are.not.allowed", currentUser.getProfile().getLanguage()));            }        }        return new ApiResponse(false, resourceBundleService.getMessage("item.not,found", currentUser.getProfile().getLanguage()));    }    public List<CommentDTO> getList(Integer id, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository.findAllByProfileId(id, pageable);        log.info("get comment list!");        return commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();    }    public Page<CommentDTO> paging(Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository.findAll(pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging comment list!");        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> pagingByProfileId(Integer id, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository.findAllByProfileId(id, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging comment list by profile id: " + id);        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> pagingByContent(String content, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository.findAllByContent(content, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging comment list by profile content: " + content);        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> pagingByCreatedDate(LocalDate date, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        LocalDateTime beginning = date.atStartOfDay();        LocalDateTime ending = beginning.plusDays(1).minusNanos(1);        Page<CommentEntity> commentEntityPage = commentRepository.findAllByCreatedDateBetween(beginning, ending, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging comment list by comment created date: " + date);        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> pagingByLikeCount(Long count, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository.findAllByLikeCount(count, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging comment list by like count: " + count);        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }    public Page<CommentDTO> pagingByDislikeCount(Long count, Integer page, Integer size) {        Sort sort = Sort.by("createdDate").descending();        Pageable pageable = PageRequest.of(page, size, sort);        Page<CommentEntity> commentEntityPage = commentRepository.findAllByDislikeCount(count, pageable);        List<CommentDTO> commentDTOList = commentEntityPage                .stream()                .map(this::TO_DTO)                .toList();        log.info("paging comment list by dislike count: " + count);        return new PageImpl<>(commentDTOList, pageable, commentEntityPage.getTotalElements());    }}