package com.grephi.be.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCondition {
    private List<String> searchCate;  //키워드 분류
    private List<String> searchText;  //검색어 입력
    private List<String> searchTextFrom;  //비교 연산자 사용시 검색어 입력
    private List<String> searchOperator;  //비교 연산자 분류
    private List<String> searchYMW;  //년월일 분류
    private String searchCateDictionary;  //키워드 분류
    private String searchTextDictionary;  //검색어 입력
    private String insertStartDate;  //시작 날짜
    private String insertEndDate;  //끝나는 날짜
    private String lastVisitStartDate;  //시작 날짜
    private String lastVisitEndDate;  //끝나는 날짜
}