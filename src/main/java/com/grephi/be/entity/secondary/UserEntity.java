package com.grephi.be.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="user", catalog = "`godhavi`")
@Entity
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDateTime last_login_at;
    private Integer level;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    private LocalDateTime deleted_date;

}
