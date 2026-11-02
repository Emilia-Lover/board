package com.example.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 50)
    private String writer;

    @Column(nullable = false)
    private int viewCount = 0;

    // 공지사항 여부 (true = 공지, false = 일반글)
    @ColumnDefault("false")
    @Column(nullable = false)
    private boolean notice = false;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // 새 글을 만들 때 사용
    public Post(String title, String content, String writer, boolean notice) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.notice = notice;
    }

    // 글을 수정할 때 사용
    public void update(String title, String content, boolean notice) {
        this.title = title;
        this.content = content;
        this.notice = notice;
    }

    // 조회수 1 올리기
    public void increaseViewCount() {
        this.viewCount++;
    }
}