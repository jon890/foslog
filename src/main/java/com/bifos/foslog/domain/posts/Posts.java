package com.bifos.foslog.domain.posts;

import com.bifos.foslog.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String tags;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostsType type;

    @Builder
    public Posts(String title, String content, String tags, PostsType type) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.type = type;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
