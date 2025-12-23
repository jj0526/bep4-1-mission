package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.domain.MemberScoreEvent;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.enums.ActivityType;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    private final EventPublisher eventPublisher;

    public long count() {
        return postRepository.count();
    }

    public void write(Member author, String title, String content) {
        Post post = new Post(author, title, content);

        postRepository.save(post);
        eventPublisher.publish(new MemberScoreEvent(author.getId(), ActivityType.POST));
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
