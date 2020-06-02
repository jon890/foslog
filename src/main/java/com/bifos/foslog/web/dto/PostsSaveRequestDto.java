package com.bifos.foslog.web.dto;

import com.bifos.foslog.domain.posts.Posts;
import com.bifos.foslog.domain.posts.PostsType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String tags;
    private PostsType type;

    @Builder
    public PostsSaveRequestDto(String title, String content, String tags, PostsType type) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.type = type;
    }

    public Posts toEntity() {
        return Posts.builder()
                    .title(title)
                    .content(content)
                    .tags(tags)
                    .type(type)
                    .build();
    }
}
