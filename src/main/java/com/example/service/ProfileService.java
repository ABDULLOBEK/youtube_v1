package com.example.service;import com.example.dto.ProfileDTO;import com.example.repository.ProfileRepository;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class ProfileService {    @Autowired    private ProfileRepository profileRepository;    public ProfileDTO create(ProfileDTO dto) {        return null;        //    }}