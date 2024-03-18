package com.grephi.be.entity.prime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="admin_user")
@Entity
public class AdminUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "admin_id")
    private String adminId;

    private String pwd;
    private String name;

    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    private String level;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
