package com.example.board.controller;

import com.example.board.dto.PostForm;
import com.example.board.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 목록 화면: GET /posts (공지 + 일반글)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("notices", postService.findNotices());
        model.addAttribute("posts", postService.findPosts());
        return "posts/list";
    }

    // 글쓰기 화면: GET /posts/new
    @GetMapping("/new")
    public String writeForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "posts/form";
    }

    // 글 저장: POST /posts
    @PostMapping
    public String write(@Valid @ModelAttribute PostForm postForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "posts/form";
        }
        postService.write(postForm);
        return "redirect:/posts";
    }

    // 상세보기: GET /posts/1
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.read(id));
        return "posts/detail";
    }

    // 수정 화면: GET /posts/1/edit
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("postForm", PostForm.from(postService.findById(id)));
        model.addAttribute("postId", id);
        return "posts/form";
    }

    // 수정 저장: POST /posts/1/edit
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute PostForm postForm,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", id);
            return "posts/form";
        }
        postService.update(id, postForm);
        return "redirect:/posts/" + id;
    }

    // 삭제: POST /posts/1/delete
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/posts";
    }
}