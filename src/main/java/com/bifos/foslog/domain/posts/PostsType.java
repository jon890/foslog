package com.bifos.foslog.domain.posts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostsType {

    DEVNOTE("DEV_NOTE", "개발 노트");

    private final String key;
    private final String title;
}
