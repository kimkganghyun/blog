package org.example.myblog.controller;

import org.example.myblog.model.Blog;
import org.example.myblog.model.User;
import org.example.myblog.repository.BlogRepository;
import org.example.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    // 블로그 페이지 조회 (사용자 이름으로)
    @GetMapping("/@{userName}")
    public Optional<Blog> getBlogByUserName(@PathVariable String userName) {
        Optional<User> user = userRepository.findByName(userName);
        if (user.isPresent()) {
            return blogRepository.findByUser(user.get());
        }
        return Optional.empty();
    }
}
