package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 유저 아이디

    @Column(nullable = false, unique = true)
    private String name; // 유저 이름

    @Column(nullable = false)
    private String email; // 유저 이메일

    @Column(nullable = false)
    private String password; // 유저 비밀번호

    private String profileImage; // 프로필 이미지

    @Column(nullable = false)
    private Boolean emailStatus = false; // 이메일 수신 여부

    @Column(nullable = false)
    private LocalDateTime creationDate; // 생성 일자
}
