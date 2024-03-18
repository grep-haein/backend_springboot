package com.grephi.be.entity.prime;

import com.grephi.be.model.BoardError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseEntity {
    private int status;
    private String message;
    private String code;

    public ErrorResponseEntity(BoardError boardError) {
        this.status = boardError.getHttpStatus().value();
        this.message = boardError.getDescription();
        this.code = boardError.getCode();
    }
}
