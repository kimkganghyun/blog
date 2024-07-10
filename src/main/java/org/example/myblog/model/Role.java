package org.example.myblog.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId; // 역할 아이디

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role; // 역할 (ENUM)

    public enum RoleType {
        READER,
        ADMIN
    }
}

