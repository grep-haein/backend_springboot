package com.grephi.be.entity.secondary;

import com.grephi.be.model.SearchCondition;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HaviRepository extends JpaRepository<HaviEntity, Integer> {
    Page<HaviEntity> findAllByOrderBySnoDesc(Pageable pageable, SearchCondition searchCondition);
}
