package com.example.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Post {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int viewCount;
    private boolean notice;           // 공지사항 여부 (true = 공지, false = 일반글)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 새 글을 만들 때 사용
    public Post(String title, String content, String writer, boolean notice) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.notice = notice;
    }

    // 글을 수정할 때 사용 (값만 바꿈, DB 저장은 Mapper가 함)
    public void update(String title, String content, boolean notice) {
        this.title = title;
        this.content = content;
        this.notice = notice;
    }
}