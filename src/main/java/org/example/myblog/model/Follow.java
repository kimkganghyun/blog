package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Follow")
public class Follow {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId; // 팔로우 아이디

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower; // 팔로워 아이디 (외래 키)

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private User following; // 팔로잉 아이디 (외래 키)
}
