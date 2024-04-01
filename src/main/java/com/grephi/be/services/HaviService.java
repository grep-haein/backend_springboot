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
        entity.setUpdated_date(LocalDateTime.now());
        entity.setHavi_description(haviDto.getHavi_description());
        return haviRepository.save(entity);
    }

    /**
     * 습관 체크
     */
    public HaviEntity checkHavi(HaviDto haviDto) {
        HaviEntity entity = haviRepository.findById(haviDto.getSno()).orElseThrow(() -> new RuntimeException("습관을 찾을 수 없습니다."));
        entity.setUpdated_date(LocalDateTime.now());
        entity.setHavi_description(haviDto.getHavi_description());
        return haviRepository.save(entity);
    }

    /**
     * 습관 삭제
     */
    public void delete(Integer sno) {
        HaviEntity entity = haviRepository.findById(sno).orElseThrow(() -> new RuntimeException("습관을 찾을 수 없습니다."));
        entity.setUpdated_date(LocalDateTime.now());
        entity.setDeleted_date(LocalDateTime.now());
        entity.setDelete_yn(true);
        haviRepository.save(entity);
    }

}
