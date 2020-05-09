package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String tags;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.tags = entity.getTags();
        this.modifiedDate = entity.getModifiedDate();
    }
}
