package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Likes")
public class Like {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId; // 좋아요 아이디

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 아이디 (외래 키)

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 게시물 아이디 (외래 키)
}
