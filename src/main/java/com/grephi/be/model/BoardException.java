package com.grephi.be.model;

import lombok.Getter;

@Getter
public class BoardException extends RuntimeException{
    private BoardError boardError;

    /**
     * Creates a new {@code BoardException} with specified {@code BoardError} and message.
     *
     * @param boardError an erx error code why this exception was thrown.
     * @param message an error message describing why this exception was thrown.
     */
    public BoardException(BoardError boardError, String message) {
        super(message);
        this.boardError = boardError;
    }

    /**
     *
     * @return
     */
    public BoardError getBoardError() {
        return boardError;
    }

    /**
     *
     * @return
     */
    public boolean isBoardError() {
        return boardError != null ? true : false;
    }

}
