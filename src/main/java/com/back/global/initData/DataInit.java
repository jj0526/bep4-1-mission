package com.back.global.initData;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.boundedContext.member.service.MemberService;
import com.back.boundedContext.post.service.CommentService;
import com.back.boundedContext.post.service.PostService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Slf4j
public class DataInit {
    private final DataInit self;
    private final MemberService memberService;
    private final PostService postService;
    private final CommentService commentService;

    public DataInit(
            @Lazy DataInit self,
            MemberService memberService,
            PostService postService, CommentService commentService
    ) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePosts();
            self.makeBasePostComments();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberService.count() > 0) return;

        Member systemMember = memberService.join("system", "1234", "시스템");
        Member holdingMember = memberService.join("holding", "1234", "홀딩");
        Member adminMember = memberService.join("admin", "1234", "관리자");
        Member user1Member = memberService.join("user1", "1234", "유저1");
        Member user2Member = memberService.join("user2", "1234", "유저2");
        Member user3Member = memberService.join("user3", "1234", "유저3");
    }

    @Transactional
    public void makeBasePosts() {
        if (postService.count() > 0) return;

        Member user1Member = memberService.findByUsername("user1").get();
        Member user2Member = memberService.findByUsername("user2").get();
        Member user3Member = memberService.findByUsername("user3").get();

        postService.write(user1Member, "제목1", "내용1");
        postService.write(user1Member, "제목2", "내용2");
        postService.write(user1Member, "제목3", "내용3");
        postService.write(user2Member, "제목4", "내용4");
        postService.write(user2Member, "제목5", "내용5");
        postService.write(user3Member, "제목6", "내용6");
    }

    @Transactional
    public void makeBasePostComments() {
        Post post1 = postService.findById(1).get();
        Post post2 = postService.findById(2).get();
        Post post3 = postService.findById(3).get();
        Post post4 = postService.findById(4).get();
        Post post5 = postService.findById(5).get();
        Post post6 = postService.findById(6).get();

        Member user1Member = memberService.findByUsername("user1").get();
        Member user2Member = memberService.findByUsername("user2").get();
        Member user3Member = memberService.findByUsername("user3").get();

        if (post1.hasComments()) return;

        commentService.addComment(post1, user1Member, "댓글1");
        commentService.addComment(post1, user2Member, "댓글2");
        commentService.addComment(post1, user3Member, "댓글3");

        commentService.addComment(post2, user2Member, "댓글4");
        commentService.addComment(post2, user2Member, "댓글5");

        commentService.addComment(post3, user3Member, "댓글6");
        commentService.addComment(post3, user3Member, "댓글7");

        commentService.addComment(post4, user1Member, "댓글8");
    }
}
