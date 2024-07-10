package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "PostTags")
public class PostTag {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postTagId; // 게시물-태그 아이디

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag; // 태그 아이디 (외래 키)

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post; // 게시물 아이디 (외래 키)
}
