package com.learning.linkedin.posts_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.linkedin.posts_service.dto.PostCreateRequestDto;
import com.learning.linkedin.posts_service.dto.PostDto;
import com.learning.linkedin.posts_service.service.PostsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostCreateRequestDto postDto, HttpServletRequest httpServletRequest) {
        PostDto createdPost = postsService.createPost(postDto,1L);
        return new ResponseEntity<>(createdPost,HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    private ResponseEntity<PostDto> getPost(@PathVariable Long postId){
        PostDto post = postsService.getPostById(postId);

        return ResponseEntity.ok(post);
    }

    @GetMapping("/users/{userId}/allPosts")
    private ResponseEntity<List<PostDto>> getAllPost(@PathVariable Long userId){
        List<PostDto> posts = postsService.getAllPostOfUser(userId);

        return ResponseEntity.ok(posts);
    }


    
}