package com.bifos.foslog.web;

import com.bifos.foslog.config.auth.LoginUser;
import com.bifos.foslog.config.auth.dto.SessionUser;
import com.bifos.foslog.domain.posts.PostsType;
import com.bifos.foslog.service.posts.PostsService;
import com.bifos.foslog.web.dto.PostsListResponseDto;
import com.bifos.foslog.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class PostsController {

    private final PostsService postsService;

    @GetMapping("/posts/list")
    public String postsList(@ModelAttribute("type") PostsType type, @LoginUser SessionUser user, Model model) {

        List<PostsListResponseDto> list = postsService.findAllDesc().stream()
                .filter(post -> type == post.getType())
                .collect(Collectors.toList());

        model.addAttribute("posts", list);

        if (user != null)
            model.addAttribute("loginUserName", user.getName());

        return "/posts/list";
    }

    @GetMapping("/posts/save")
    public String postsSave(@ModelAttribute("type") PostsType type, @LoginUser SessionUser user, Model model) {
        if (user != null)
            model.addAttribute("loginUserName", user.getName());
        return "/posts/save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
