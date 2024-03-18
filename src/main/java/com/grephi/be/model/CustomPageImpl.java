package com.grephi.be.model;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomPageImpl<T> extends PageImpl<T> {
    private final long listCount;

    public CustomPageImpl(List<T> content, Pageable pageable, long total, long list_count) {
        super(content, pageable, total);
        this.listCount = list_count;
    }

    public long getListCount() {
        return listCount;
    }
}