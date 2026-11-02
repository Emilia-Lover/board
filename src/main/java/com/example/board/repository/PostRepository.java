package com.example.board.repository;

import com.example.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 공지글만, 최신순
    List<Post> findByNoticeTrueOrderByIdDesc();

    // 일반글만, 최신순
    List<Post> findByNoticeFalseOrderByIdDesc();
}