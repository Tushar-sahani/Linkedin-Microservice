package com.learning.linkedin.posts_service.service;

import com.learning.linkedin.posts_service.exception.BadRequestException;
import org.springframework.stereotype.Service;

import com.learning.linkedin.posts_service.entity.PostLike;
import com.learning.linkedin.posts_service.exception.ResourceNotFoundException;
import com.learning.linkedin.posts_service.repository.PostLikeRepository;
import com.learning.linkedin.posts_service.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId,Long userId){
        log.info("Attempting to like the post with id: {}",postId);
        boolean exists = postRepository.existsById(postId);

        if(!exists) throw new ResourceNotFoundException("Post not found with id: "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(alreadyLiked) throw new BadRequestException("Cannot like the same post again.");

        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        postLikeRepository.save(postLike);
        log.info(" post with id: {} liked successfully",postId);
    }

    public void unlikePost(Long postId,Long userId){
        log.info("Attempting to unlikePost the post with id: {}",postId);
        boolean exists = postRepository.existsById(postId);

        if(!exists) throw new ResourceNotFoundException("Post not found with id: "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId, postId);
        if(!alreadyLiked) throw new BadRequestException("Cannot unlikePost the post which is not liked.");

        postLikeRepository.deleteByUserIdAndPostId(userId, postId);
        log.info(" unliked post with id: {}",postId);
    }

}
