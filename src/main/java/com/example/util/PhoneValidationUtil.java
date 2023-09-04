package com.example.util;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneValidationUtil  {
    //    @Override
    public Boolean test(String s) {
        if (s == null || s.isBlank()) {
            return false;
        }
        Pattern p = Pattern.compile("[+]998[1-9][0-9]{9}");//. represents single character
        Matcher m = p.matcher(s);
        return m.matches();
    }
}