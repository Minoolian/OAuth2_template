package com.codestates.section4week1.model;

import com.codestates.section4week1.config.auth.ProviderType;
import com.codestates.section4week1.config.auth.RoleType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDateTime createdAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Builder
    public Member(String userId, String username, String email, String role, ProviderType providerType, RoleType roleType) {
        this.userId = userId;
        this.username = username;
        this.password = "test1234";
        this.email = email;
        this.role = role;
        this.providerType = providerType;
        this.roleType = roleType;
    }
}
