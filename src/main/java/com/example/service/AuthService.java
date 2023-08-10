package com.example.service;import com.example.dto.ApiResponse;import com.example.dto.AuthDTO;import com.example.dto.ProfileDTO;import com.example.dto.RegistrationDTO;import com.example.entity.EmailHistoryEntity;import com.example.entity.ProfileEntity;import com.example.enums.ProfileRole;import com.example.enums.ProfileStatus;import com.example.repository.EmailHistoryRepository;import com.example.repository.ProfileRepository;import com.example.util.EmailRandomUtil;import com.example.util.JWTUtil;import com.example.util.MD5Util;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.time.LocalDateTime;import java.util.Optional;@Servicepublic class AuthService {    @Autowired    private ProfileRepository profileRepository;    @Autowired    private EmailHistoryRepository emailHistoryRepository;    @Autowired    private EmailSenderService emailSenderService;    public ApiResponse login(AuthDTO dto) {        Optional<ProfileEntity> byEmail = profileRepository.findByEmail(dto.getEmail());        if (byEmail.isPresent()) {            ProfileEntity entity = byEmail.get();            return new ApiResponse(true, TO_DTO(entity));        }        return new ApiResponse(false, "PROFILE NOT FOUND!");    }    public ApiResponse registration(RegistrationDTO registrationDTO) {        Optional<ProfileEntity> optionalProfile = profileRepository.findByEmail(registrationDTO.getEmail());        if (optionalProfile.isPresent()) {            if (optionalProfile.get().getStatus().equals(ProfileStatus.REGISTRATION)) {                profileRepository.delete(optionalProfile.get()); // delete            } else {                return new ApiResponse(false, "EMAIL ALREADY EXIST!");            }        }        Long count = emailHistoryRepository.countAllByEmailAndCreatedDateAfter(registrationDTO.getEmail(), LocalDateTime.now().minusMinutes(1));        if (count > 4) {            return new ApiResponse(false, "TRY AGAIN LATER AROUND 1 MINUTE!!");        }        ProfileEntity entity = new ProfileEntity();        entity.setName(registrationDTO.getName());        entity.setSurname(registrationDTO.getSurname());        entity.setEmail(registrationDTO.getEmail());        entity.setPassword(MD5Util.encode(registrationDTO.getPassword()));        entity.setRole(ProfileRole.ROLE_USER);        entity.setStatus(ProfileStatus.REGISTRATION);        profileRepository.save(entity);        return emailSenderService.sendEmailVerification(registrationDTO.getEmail());    }    private ProfileDTO TO_DTO(ProfileEntity entity) {        ProfileDTO dto = new ProfileDTO();        dto.setEmail(entity.getEmail());        dto.setId(entity.getId());        dto.setRole(entity.getRole());        dto.setStatus(entity.getStatus());        dto.setName(entity.getName());        dto.setSurname(entity.getSurname());//        dto.setPhotoId(entity.getPhotoId()); // TODO        dto.setJwt(JWTUtil.encode(entity.getEmail()));        return dto;    }}