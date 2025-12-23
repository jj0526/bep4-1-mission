package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.domain.MemberScoreEvent;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.enums.ActivityType;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteUseCase {

    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public void write(Member author, String title, String content) {
        Post post = new Post(author, title, content);

        postRepository.save(post);
        eventPublisher.publish(new MemberScoreEvent(author.getId(), ActivityType.POST));
    }
}
