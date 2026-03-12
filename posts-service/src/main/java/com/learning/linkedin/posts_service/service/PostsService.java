package com.learning.linkedin.posts_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.learning.linkedin.posts_service.dto.PostCreateRequestDto;
import com.learning.linkedin.posts_service.dto.PostDto;
import com.learning.linkedin.posts_service.entity.Post;
import com.learning.linkedin.posts_service.exception.ResourceNotFoundException;
import com.learning.linkedin.posts_service.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostsService {
    
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postDto,Long userId) {

        Post post = modelMapper.map(postDto,Post.class);

        post.setUserId(userId);

        Post savePost = postRepository.save(post);

        return modelMapper.map(savePost,PostDto.class);
    }

    public PostDto getPostById(Long postId) {

        log.debug("Retrieving post with id:{}",postId);
      Post postDto= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post not found with id: "+postId));
      return modelMapper.map(postDto,PostDto.class);
    }

    public List<PostDto> getAllPostOfUser(Long userId){
        List<Post> posts = postRepository.findByUserId(userId);

        return posts.stream()
                .map((element)-> modelMapper.map(element,PostDto.class))
                .collect(Collectors.toList());
    }
}
