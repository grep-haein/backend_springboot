package com.grephi.be.entity.secondary;

import com.grephi.be.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HavidaysRepository extends JpaRepository<HavidaysEntity, Integer> {
    Page<HavidaysEntity> findAllByOrderBySnoDesc(Pageable pageable, SearchCondition searchCondition);
    Page<HavidaysEntity> countAllBySno(Integer sno);
}
