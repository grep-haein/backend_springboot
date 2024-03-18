package com.grephi.be.entity.prime;

/* entity/BoardRepositoryCustom.java */

import com.grephi.be.config.querydsl.PrimaryQuerydslRepositorySupport;
import com.grephi.be.entity.secondary.ClientEntity;
import com.grephi.be.model.CustomPageImpl;
import com.grephi.be.model.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.grephi.be.entity.prime.QCmsPetDictionaryEntity.cmsPetDictionaryEntity;


//@RequiredArgsConstructor
@Repository
public class CmsPetDictionaryRepositoryCustom extends PrimaryQuerydslRepositorySupport {

//    private final JPAQueryFactory queryFactory;
    private final JPAQueryFactory primaryQueryFactory;

    public CmsPetDictionaryRepositoryCustom(JPAQueryFactory primaryQueryFactory){
        super(ClientEntity.class);
        this.primaryQueryFactory = primaryQueryFactory;
    }

    public Page<CmsPetDictionaryEntity> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition, Integer firstId, Integer lastId, boolean orderStatus, Integer showStatus) {
        JPAQuery<CmsPetDictionaryEntity> query = primaryQueryFactory.selectFrom(cmsPetDictionaryEntity)
                .where(cmsPetDictionaryEntity.deleted_at.isNull(), searchKeywords(searchCondition.getSearchCateDictionary()), searchText(searchCondition.getSearchTextDictionary())
                        ,dynamicId(firstId,lastId,orderStatus),isShow(showStatus));

        long list_count = query.fetch().size();
//        long total = query.stream().count();
//
//        List<CmsPetDictionaryEntity> results = query
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .orderBy(cmsPetDictionaryEntity.idx.desc())
//                .fetch();
        List<CmsPetDictionaryEntity> results;
        if(!orderStatus) { //orderStatus == 0일때 (desc 정렬일 때(기본값))
            if (firstId.equals(lastId) && firstId != 0) { //이전 페이지로 돌아갈 때
                results = query
                        .orderBy(cmsPetDictionaryEntity.idx.asc())
                        .limit(pageable.getPageSize()).fetch();
            } else {
                results = query
                        .orderBy(cmsPetDictionaryEntity.idx.desc())
                        .limit(pageable.getPageSize()).fetch();
            }
        } else { //orderStatus == 1일때 (asc 정렬일 때)
            if (firstId.equals(lastId) && firstId != 0) { //이전 페이지로 돌아갈 때
                results = query
                        .orderBy(cmsPetDictionaryEntity.idx.desc())
                        .limit(pageable.getPageSize()).fetch();
            } else {
                results = query
                        .orderBy(cmsPetDictionaryEntity.idx.asc())
                        .limit(pageable.getPageSize()).fetch();
            }
        }
        long total = results.size();
//        return new PageImpl<>(results, pageable, total);
        return new CustomPageImpl<>(results, pageable, total,list_count);
    }

    private BooleanExpression searchText(String searchTextDictionary) {
        if (StringUtils.hasLength(searchTextDictionary)) {
            return cmsPetDictionaryEntity.title.contains(searchTextDictionary);
        }
        return null;
    }

    private BooleanExpression searchKeywords(String searchCateDictionary) {
        if ("개".equals(searchCateDictionary)) {
            return cmsPetDictionaryEntity.species_kind.contains("\"1\"");
        } else if ("고양이".equals(searchCateDictionary)) {
            return cmsPetDictionaryEntity.species_kind.contains("\"2\"");
        } else if ("개,고양이외".equals(searchCateDictionary)) {
            // 개, 고양이 문자 제외 조건
            return cmsPetDictionaryEntity.species_kind.notLike("%%\"1\"%%").and(cmsPetDictionaryEntity.species_kind.notLike("%%\"2\"%%"));
            // 개,고양이외 / 인수공통 / 법정 전염병 문자가 들어간 조건
//            return cmsPetDictionaryEntity.species_kind.contains("\"3\"").or(cmsPetDictionaryEntity.species_kind.contains("\"4\"")).or(cmsPetDictionaryEntity.species_kind.contains("\"5\""));
        } else if ("인수공통".equals(searchCateDictionary)) {
            return cmsPetDictionaryEntity.species_kind.contains("\"4\"");
        } else if ("법정 전염병".equals(searchCateDictionary)) {
            return cmsPetDictionaryEntity.species_kind.contains("\"5\"");
        } else {
            return null;
        }
    }

    private BooleanExpression dynamicId(Integer firstId, Integer lastId,boolean orderStatus) {
        if(firstId.equals(0) && lastId.equals(0)) {
            return null;
        }
        if(!orderStatus) {
            if (firstId.equals(lastId)) {
                return cmsPetDictionaryEntity.idx.gt(firstId);
            }
            return cmsPetDictionaryEntity.idx.lt(lastId);
        } else {
            if (firstId.equals(lastId)) {
                return cmsPetDictionaryEntity.idx.lt(firstId);
            }
            return cmsPetDictionaryEntity.idx.gt(lastId);
        }
    }

    private BooleanExpression isShow(Integer showStatus) {
        if(showStatus.equals(0)) {
            return null;
        }
        if(showStatus.equals(1)) {
            return cmsPetDictionaryEntity.is_show.eq(1);  // 게시
        } else if (showStatus.equals(2)) {
            return cmsPetDictionaryEntity.is_show.eq(0);  // 게시안함
        }
        return null;
    }
}
