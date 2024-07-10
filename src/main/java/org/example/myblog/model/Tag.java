package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Tags")
public class Tag {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId; // 태그 아이디

    @Column(nullable = false)
    private String name; // 태그 이름
}
