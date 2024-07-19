package org.example.myblog.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.myblog.model.Blog;
import org.example.myblog.model.User;
import org.example.myblog.repository.BlogRepository;
import org.example.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    // 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 특정 사용자 조회
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // 사용자 이름으로 조회
    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }

    // 사용자 이메일로 조회
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 회원가입
    public User registerUser(User user) {
        if (user.getEmailStatus() == null) {
            user.setEmailStatus(false); // 기본값 설정
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        // 회원가입 시 블로그 생성
        Blog blog = new Blog();
        blog.setTitle(savedUser.getName() + "'s Blog");
        blog.setUser(savedUser);
        blogRepository.save(blog);

        return savedUser;
    }

    // 로그인 (단순히 사용자 존재 여부 확인)
    public boolean loginUser(String name, String password, HttpServletResponse response) {
        Optional<User> user = userRepository.findByName(name);
        if (user.isPresent()) {
            boolean passwordMatches = passwordEncoder.matches(password, user.get().getPassword());
            System.out.println("User found: " + user.get().getName());
            System.out.println("Password matches: " + passwordMatches);
            if (passwordMatches) {
                session.setAttribute("user", user.get());

                // 쿠키 설정
                Cookie cookie = new Cookie("userName", user.get().getName());
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return passwordMatches;
        }
        System.out.println("User not found with name: " + name);
        return false;
    }

    // 사용자 업데이트
    public User updateUser(Long userId, User userDetails) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setProfileImage(userDetails.getProfileImage());
            user.setEmailStatus(userDetails.getEmailStatus());
            return userRepository.save(user);
        }
        return null;
    }

    // 사용자 삭제
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
