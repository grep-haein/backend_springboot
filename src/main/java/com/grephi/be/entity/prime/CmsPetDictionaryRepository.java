package com.grephi.be.entity.prime;

import com.grephi.be.model.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmsPetDictionaryRepository extends JpaRepository<CmsPetDictionaryEntity, Long> {
    Page<CmsPetDictionaryEntity> findAllByOrderByIdxDesc(Pageable pageable, SearchCondition searchCondition);
}
