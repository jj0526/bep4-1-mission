package com.back.shared.post.dto;

import com.back.boundedContext.post.domain.Post;
import com.back.standard.modelType.HasModelTypeCode;

import java.time.LocalDateTime;

public record PostDto(
        long id,
        LocalDateTime createDate,
        LocalDateTime modifyDate,
        long authorId,
        String authorName,
        String title,
        String content
) implements HasModelTypeCode {
    @Override
    public String getModelTypeCode() {
        return "Post";
    }

    public PostDto(Post post) {
        this(
                post.getId(),
                post.getCreateDate(),
                post.getModifyDate(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getTitle(),
                post.getContent()
        );
    }
}
