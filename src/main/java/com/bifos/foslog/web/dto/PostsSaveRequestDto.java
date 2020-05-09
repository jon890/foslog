package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String tags;

    @Builder
    public PostsSaveRequestDto(String title, String content, String tags) {
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public Posts toEntity() {
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .tags(tags)
                    .build();
    }
}
