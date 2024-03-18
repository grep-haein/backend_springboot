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
@Table(name="cms_pet_dictionary")
@Entity
public class CmsPetDictionaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String species_kind;
    private String species_kind_search;
    private String title;
    private String contents;
    private String thumb_nail;
    private String img_infos;
    private int emergency;
    private int frequency;
    private String clinic_cate;
    private String keyword;
    private int view_cnt;
    private int is_show;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

}
