package org.example.myblog.repository;

import org.example.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name); // 사용자 이름으로 사용자 조회
    Optional<User> findByEmail(String email); // 사용자 이메일로 사용자 조회
}
