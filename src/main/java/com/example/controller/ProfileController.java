package com.example.controller;import com.example.service.ProfileService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;@RestController@RequestMapping("/api/v1/profile")public class ProfileController {    @Autowired    private ProfileService profileService;}