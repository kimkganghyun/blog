package org.example.myblog.repository;

import org.example.myblog.model.Blog;
import org.example.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    Optional<Blog> findByUser(User user); // 사용자로 블로그 조회
}
