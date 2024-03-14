package com.grephi.be.web.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable {
    private Long idx;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
    private List<String> deletedFileList;
    @JsonProperty("isFileChange")
    private boolean isFileChange;
    @JsonProperty("imgUploadFileName")
    private String imgUploadFileName;
    @JsonProperty("deletedImgUploadFileUrl")
    private String deletedImgUploadFileUrl;
    private String img_infos;
    private String thumb_nail;
}
