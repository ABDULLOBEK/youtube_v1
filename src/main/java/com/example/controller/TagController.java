package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.TagDTO;
import com.example.entity.TagEntity;
import com.example.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody TagDTO dto){
        TagDTO response = tagService.add(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@RequestBody TagDTO dto,
                                 @PathVariable("id") Integer id){
        tagService.update(id, dto);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        String  response = tagService.delete(id);
        if(response.length()>0){
            return ResponseEntity.ok("Tag Deleted");
        }
        return ResponseEntity.badRequest().body("Tag not found");
    }

    @GetMapping("/all")
    public List<TagDTO> all(){
        return tagService.getAll();
    }
}
