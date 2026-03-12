package com.learning.linkedin.posts_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.linkedin.posts_service.entity.PostLike;

import jakarta.transaction.Transactional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long>{
    boolean existsByUserIdAndPostId(Long userId,Long postId);

    @Transactional
    void deleteByUserIdAndPostId(Long userId, Long postId);
}
