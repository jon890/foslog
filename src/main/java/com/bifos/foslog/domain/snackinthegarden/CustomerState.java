package com.bifos.foslog.domain.snackinthegarden;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerState {

    REQUEST_DELETED("REQUEST_DELETED", "사용자 정보 삭제 요청"),
    
    NOT_USED("NOT_USED", "배달 시작 전"),
    DELIVERING("DELIVERING", "배달 중");

    private final String key;
    private final String name;
}
