package com.example.board.service;

import com.example.board.dto.PostForm;
import com.example.board.entity.Post;
import com.example.board.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostMapper postMapper;

    // 공지글 목록
    public List<Post> findNotices() {
        return postMapper.findNotices();
    }

    // 일반글 목록
    public List<Post> findPosts() {
        return postMapper.findPosts();
    }

    // 글 하나 찾기 (없으면 에러)
    public Post findById(Long id) {
        return postMapper.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않아요. id=" + id));
    }

    // 상세보기 (조회수 +1 한 다음 글 꺼내기)
    @Transactional
    public Post read(Long id) {
        postMapper.increaseViewCount(id);
        return findById(id);
    }

    // 글 저장
    @Transactional
    public Long write(PostForm form) {
        Post post = new Post(form.getTitle(), form.getContent(), form.getWriter(), form.isNotice());
        postMapper.insert(post);
        return post.getId();
    }

    // 글 수정
    @Transactional
    public void update(Long id, PostForm form) {
        Post post = findById(id);
        post.update(form.getTitle(), form.getContent(), form.isNotice());
        postMapper.update(post);   // ⭐ MyBatis는 직접 UPDATE를 불러야 저장돼요
    }

    // 글 삭제
    @Transactional
    public void delete(Long id) {
        findById(id);              // 없는 글이면 여기서 에러
        postMapper.delete(id);
    }
}