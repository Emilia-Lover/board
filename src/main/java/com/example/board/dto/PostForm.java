package com.example.board.dto;

import com.example.board.entity.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {

    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(max = 200, message = "제목은 200자까지 쓸 수 있어요.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @NotBlank(message = "작성자를 입력해 주세요.")
    @Size(max = 50, message = "작성자는 50자까지 쓸 수 있어요.")
    private String writer;

    // 공지사항 체크박스
    private boolean notice;

    // 기존 글(Post)의 내용을 PostForm에 옮겨 담기 (수정 화면용)
    public static PostForm from(Post post) {
        PostForm form = new PostForm();
        form.setTitle(post.getTitle());
        form.setContent(post.getContent());
        form.setWriter(post.getWriter());
        form.setNotice(post.isNotice());
        return form;
    }
}