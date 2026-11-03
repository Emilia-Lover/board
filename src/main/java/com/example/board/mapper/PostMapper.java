package com.example.board.mapper;

import com.example.board.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    // 공지글만, 최신순
    List<Post> findNotices();

    // 일반글만, 최신순
    List<Post> findPosts();

    // 글 하나 찾기
    Optional<Post> findById(Long id);

    // 글 저장 (저장 후 post.id에 새 번호가 채워짐)
    void insert(Post post);

    // 글 수정
    void update(Post post);

    // 조회수 +1
    void increaseViewCount(Long id);

    // 글 삭제
    void delete(Long id);
}