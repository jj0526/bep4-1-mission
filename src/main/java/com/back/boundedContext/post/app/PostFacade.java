package com.back.boundedContext.post.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostMemberRepository;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.RsData.RsData;
import com.back.global.exception.DomainException;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {
    private final PostRepository postRepository;
    private final PostWriteUseCase postWriteUseCase;
    private final PostMemberRepository postMemberRepository;

    public long count() {
        return postRepository.count();
    }

    @Transactional
    public RsData<Post> write(PostMember author, String title, String content) {
        return postWriteUseCase.write(author, title, content);
    }

    @Transactional
    public PostMember syncMember(MemberDto memberDto) {
        PostMember postMember = PostMember.sync(memberDto);
        return postMemberRepository.save(postMember);
    }

    @Transactional(readOnly = true)
    public PostMember findPostMemberByUsername(String username) {
        return postMemberRepository.findByUsername(username)
                .orElseThrow(() -> new DomainException("400", "존재하지 않는 게시물 회원입니다."));
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
