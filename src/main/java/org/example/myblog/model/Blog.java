package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Blogs")
public class Blog {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blogId; // 블로그 아이디

    @Column(nullable = false, unique = true)
    private String title; // 블로그 제목

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 유저 아이디 (외래 키)
}
