package com.example.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ErrorEnum {

    SUCCESS("0000","SUCCESS"),
    NEED_CATEGORY("1000", "NEW BOOK NEEDS AT LEAST ONE CATEGORY"),
    INVALID_CATEGORY("1001", "CATEGORY DOESN'T EXIST"),
    INVALID_VALUE("1002", "ISAVAILABLE SHOULD BE 'TRUE' OR 'FALSE'"),
    NOT_FOUND_BOOK("1003", "BOOK DOESN'T EXIST"),
    NOT_AVAILABLE_BOOK("1004", "BOOK IS NOT AVAILABLE"),
    UNKNOWN_ERROR("9999","UNKNOWN_ERROR"),
    ;
    private final String code;
    private final String message;
}
