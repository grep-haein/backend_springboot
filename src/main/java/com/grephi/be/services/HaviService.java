package com.grephi.be.services;

import com.grephi.be.entity.secondary.HaviEntity;
import com.grephi.be.model.Header;
import com.grephi.be.model.Pagination;
import com.grephi.be.model.SearchCondition;
import com.grephi.be.entity.secondary.HaviRepository;
import com.grephi.be.web.dtos.HaviDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class HaviService {

    private final HaviRepository haviRepository;

    /**
     * 습관 목록 가져오기
     */
    public Header<List<HaviDto>> getHaviList(Pageable pageable, SearchCondition searchCondition) {
        List<HaviDto> dtos = new ArrayList<>();

        Page<HaviEntity> boardEntities = haviRepository.findAllByOrderBySnoDesc(pageable, searchCondition);
        for (HaviEntity entity : boardEntities) {
            HaviDto dto = HaviDto.builder()
                    .sno(entity.getSno())
                    .created_date(entity.getCreated_date())
                    .updated_date(entity.getUpdated_date())
                    .deleted_date(entity.getDeleted_date())
                    .havi_name(entity.getHavi_name())
                    .havi_description(entity.getHavi_description())
                    .havi_status(entity.getHavi_status())
                    .havi_start_date(entity.getHavi_start_date())
                    .havi_end_date(entity.getHavi_end_date())
                    .build();

            dtos.add(dto);
        }

        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements()
                , pageable.getPageNumber() + 1
                , pageable.getPageSize()
                , 10
        );

        return Header.OK(dtos, pagination);
    }

    /**
     * 등록된 습관 가져오기
     */
    public HaviDto getHaviDetail(Integer sno) {
        HaviEntity entity = haviRepository.findById(sno).orElseThrow(() -> new RuntimeException("습관을 찾을 수 없습니다."));
        return HaviDto.builder()
                .sno(entity.getSno())
                .havi_name(entity.getHavi_name())
                .havi_description(entity.getHavi_description())
                .havi_status(entity.getHavi_status())
                .havi_start_date(entity.getHavi_start_date())
                .havi_end_date(entity.getHavi_end_date())
                .build();
    }

    /**
     * 습관 등록
     */
    public HaviEntity create(HaviDto boardDto) {
        HaviEntity entity = HaviEntity.builder()
                .havi_name(boardDto.getHavi_name())
                .havi_description(boardDto.getHavi_description())
                .build();
        List<HaviEntity> dtos = new ArrayList<>();
        return haviRepository.save(entity);
    }

    /**
     * 습관 수정
     */
    public HaviEntity update(HaviDto haviDto) {
        HaviEntity entity = haviRepository.findById(haviDto.getSno()).orElseThrow(() -> new RuntimeException("습관을 찾을 수 없습니다."));
        entity.setHavi_description(haviDto.getHavi_description());
        return haviRepository.save(entity);
    }

    /**
     * 습관 체크
     */
    public HaviEntity checkHavi(HaviDto haviDto) {
        HaviEntity entity = haviRepository.findById(haviDto.getSno()).orElseThrow(() -> new RuntimeException("습관을 찾을 수 없습니다."));
        entity.setHavi_description(haviDto.getHavi_description());
        return haviRepository.save(entity);
    }

    /**
     * 습관 삭제
     */
    public void delete(Integer sno) {
        HaviEntity entity = haviRepository.findById(sno).orElseThrow(() -> new RuntimeException("습관을 찾을 수 없습니다."));
        haviRepository.delete(entity);
    }
//
//    /**
//     * 이미지 업로드
//     */
//    public void uploadImageFile(BoardDto boardDto, MultipartFile imgUploadFile) {
//        //파일이름 UUID 랜덤 생성.확장자 형식으로 변환하여 저장
//        // 업로드 이미지 이름
//        String oriFileName = boardDto.getImgUploadFileName();
//        // 업로드 이미지 확장자
//        String extension = oriFileName.substring(oriFileName.lastIndexOf("."));
//        // 변환된 업로드 이미지 이름
//        UUID uuid = UUID.randomUUID();
//        // 업로드 이미지 저장 폴더 경로
//        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
//        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
//        Date date = new Date();
//        String filePathDepth1 = yearFormat.format(date);
//        String filePathDepth2 = monthFormat.format(date);
//        // 업로드 이미지가 실제 저장되는 경로 (ex: D:/temp/2023/01/uuid.jpg)
////        String filePath = uploadDir + filePathDepth1 + "\\" + filePathDepth2 + "\\" + uuid + extension;
//        String filePath = uploadDir + filePathDepth1 + "/" + filePathDepth2 + "/" + uuid + extension;
//        // db에 저장되어 있는 url 정보
//        String temp = boardDto.getDeletedImgUploadFileUrl();
//
//        // 한글 -> 유니코드 변환
//        StringBuffer sb = new StringBuffer();
//        // 글자를 하나하나 탐색한다.
//        int code = 0;
//        for (int i = 0; i < oriFileName.length(); i++) {
//            // 글자 추츨 int값으로 가져온다.
//            code = oriFileName.codePointAt(i);
//            if (code < 128) {  // 128이하면 ascii코드로 변환하지 않는다.
//                sb.append(String.format("%c", code));
//            } else {  // 16진수 유니코드로 변환한다.
//                sb.append(String.format("\\u%04x", code));
//            }
//        }
//        // 결과 리턴
//        String unicodeFileName = sb.toString();
//
//
//        // 업로드 이미지 파일이 변했거나, 새로운 글 작성일 경우
//        // db에 들어갈 thum_nail, img_Infos 정보 넣음
//        if (boardDto.isFileChange() || boardDto.getIdx() == null) {
//            boardDto.setThumb_nail("http://" + getUploadDir + ":" + getPort + "/upload/img/" + filePathDepth1 + "/" + filePathDepth2 + "/" + uuid + extension);
//            boardDto.setImg_infos("{\"org_name\":\"" + unicodeFileName + "\"}");
//
//            try {
//                File folder = new File(filePath);
//                if (!folder.exists()) { // 업로드 이미지가 실제 저장될 디렉토리가 없을 경우 생성
//                    folder.mkdirs();
//                }
//                // 업로드 이미지 디렉토리 내 저장
//                imgUploadFile.transferTo(new File(filePath));
//
//                //수정 시 기존 이미지 삭제
//                if (boardDto.getIdx() != null) {
//                    extension = boardDto.getDeletedImgUploadFileUrl().substring(boardDto.getDeletedImgUploadFileUrl().lastIndexOf("/") - 7);
//                    File deleteFile = new File(uploadDir + extension);
//                    deleteFile.delete();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else { // 업로드 이미지 파일 변경 없이 내용만 수정일 경우
//            boardDto.setThumb_nail(temp);
//            boardDto.setImg_infos("{\"org_name\":\"" + unicodeFileName + "\"}");
//        }
//    }
}
