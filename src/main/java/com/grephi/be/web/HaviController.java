package com.grephi.be.web;

import com.grephi.be.entity.secondary.HaviEntity;
import com.grephi.be.model.Header;
import com.grephi.be.model.SearchCondition;
import com.grephi.be.services.HaviService;
import com.grephi.be.web.dtos.HaviDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class HaviController {
    private final HaviService haviService;

    @GetMapping("/havi/list")
    public Header<List<HaviDto>> getHaviList(@PageableDefault(sort = {"idx"}) Pageable pageable, SearchCondition searchCondition) {
        return haviService.getHaviList(pageable, searchCondition);
    }

    @GetMapping("/havi/{id}")
    public HaviDto getHaviDetail(@PathVariable Integer sno) {
        return haviService.getHaviDetail(sno);
    }

    @PostMapping("/newhavi")
    public HaviEntity create(@RequestBody HaviDto haviDto) {
        return haviService.create(haviDto);
    }

    @DeleteMapping("/havi/{id}")
    public void delete(@PathVariable Integer id) {
        haviService.delete(id);
    }
}
