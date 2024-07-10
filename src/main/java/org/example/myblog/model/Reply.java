package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Replies")
public class Reply {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId; // 답글 아이디

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment; // 댓글 아이디 (외래 키)

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 아이디 (외래 키)

    @Column(nullable = false)
    private String text; // 답글 내용

    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성 일자
}
