package org.example.myblog.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Comments")
public class Comment {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId; // 댓글 아이디

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 아이디 (외래 키)

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 게시물 아이디 (외래 키)

    @Column(nullable = false)
    private String text; // 댓글 내용

    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성 일자
}
