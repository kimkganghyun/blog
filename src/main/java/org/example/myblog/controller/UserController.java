package org.example.myblog.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.example.myblog.model.User;
import org.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    // 모든 사용자 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 특정 사용자 조회
    @GetMapping("/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    // 사용자 이름으로 조회
    @GetMapping("/name/{name}")
    public boolean getUserByName(@PathVariable String name) {
        return userService.getUserByName(name).isPresent();
    }

    // 사용자 이메일로 조회
    @GetMapping("/email/{email}")
    public boolean getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email).isPresent();
    }

    // 회원가입
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        System.out.println("Login attempt: " + loginRequest.getEmail());
        boolean loginSuccess = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (loginSuccess) {
            // JWT 또는 세션 ID를 생성하고 쿠키에 설정
            String token = "generated-jwt-token";  // 실제 JWT 토큰 생성 로직 필요
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok().body("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패");
        }
    }

    // 로그인 상태 확인
    @GetMapping("/me")
    public User getCurrentUser() {
        return (User) session.getAttribute("user");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body("로그아웃 성공");
    }

    // 사용자 업데이트
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User userDetails) {
        return userService.updateUser(userId, userDetails);
    }

    // 사용자 삭제
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}

@Getter
@Setter
class LoginRequest {
    private String email;
    private String password;
}
