package com.grephi.be.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HaviDto implements Serializable {
    private Integer sno;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    private LocalDateTime deleted_date;
    private String havi_name;
    private String havi_description;
    private Boolean havi_status;
    private Date havi_start_date;
    private Date havi_end_date;
}
