package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId; // 시리즈 아이디

    @Column(nullable = false)
    private String title; // 시리즈 제목

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog; // 블로그 아이디 (외래 키)
}

