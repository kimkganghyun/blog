package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Posts")
public class Post {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId; // 게시물 아이디

    @Column(nullable = false)
    private String title; // 게시물 제목

    @Column(nullable = false)
    private String content; // 게시물 내용

    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성 일자

    private LocalDateTime updatedAt; // 업데이트 일자

    private LocalDateTime publishedAt; // 게시 일자

    private String image; // 이미지

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 아이디 (외래 키)

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog; // 블로그 아이디 (외래 키)

    @Column(nullable = false)
    private int likes; // 좋아요 수
}
