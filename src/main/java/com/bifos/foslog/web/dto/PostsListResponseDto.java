package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.posts.Posts;
import com.bifos.foslog.domain.posts.PostsType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String tags;
    private final PostsType type;
    private final LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.tags = entity.getTags();
        this.type = entity.getType();
        this.modifiedDate = entity.getModifiedDate();
    }
}
