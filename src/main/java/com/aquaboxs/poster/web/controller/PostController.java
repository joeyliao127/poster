package com.aquaboxs.poster.web.controller;

import com.aquaboxs.poster.Model.PostModel;
import com.aquaboxs.poster.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/api/posts")
    public ResponseEntity<List<PostModel>> getPosts(){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
    }
}
