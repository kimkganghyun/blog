package org.example.myblog.repository;

import org.example.myblog.model.Blog;
import org.example.myblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc(); // 최신 게시물 정렬
    List<Post> findAllByOrderByLikesDesc(); // 인기 게시물 정렬

    List<Post> findByBlog(Blog blog);
}
