package com.example.board.service;

import com.example.board.dto.PostForm;
import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    // 공지글 목록
    public List<Post> findNotices() {
        return postRepository.findByNoticeTrueOrderByIdDesc();
    }

    // 일반글 목록
    public List<Post> findPosts() {
        return postRepository.findByNoticeFalseOrderByIdDesc();
    }

    // 글 하나 찾기 (없으면 에러)
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않아요. id=" + id));
    }

    // 상세보기 (조회수 +1)
    @Transactional
    public Post read(Long id) {
        Post post = findById(id);
        post.increaseViewCount();
        return post;
    }

    // 글 저장
    @Transactional
    public Long write(PostForm form) {
        Post post = new Post(form.getTitle(), form.getContent(), form.getWriter(), form.isNotice());
        return postRepository.save(post).getId();
    }

    // 글 수정
    @Transactional
    public void update(Long id, PostForm form) {
        Post post = findById(id);
        post.update(form.getTitle(), form.getContent(), form.isNotice());
    }

    // 글 삭제
    @Transactional
    public void delete(Long id) {
        postRepository.delete(findById(id));
    }
}