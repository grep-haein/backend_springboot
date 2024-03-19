package com.grephi.be.entity.secondary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="havi", catalog = "`godhavi`")
@Entity
public class HaviEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sno;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    private LocalDateTime deleted_date;
    private String havi_name;
    private String havi_description;
    private Boolean havi_status;
    private Date havi_start_date;
    private Date havi_end_date;
    private Boolean delete_yn;
}
