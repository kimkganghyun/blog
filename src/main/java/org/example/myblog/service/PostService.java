package org.example.myblog.service;

import org.example.myblog.model.Blog;
import org.example.myblog.model.Post;
import org.example.myblog.model.User;
import org.example.myblog.repository.BlogRepository;
import org.example.myblog.repository.PostRepository;
import org.example.myblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    // 모든 게시물 조회
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 특정 게시물 조회
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    // 새로운 게시물 작성
    public Post createPost(Post post, Long userId, Long blogId) {
        User user = userRepository.findById(userId).orElse(null);
        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (user != null && blog != null) {
            post.setUser(user);
            post.setBlog(blog);
            return postRepository.save(post);
        }
        return null;
    }

    // 게시물 업데이트
    public Post updatePost(Long postId, Post postDetails) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            post.setImage(postDetails.getImage());
            return postRepository.save(post);
        }
        return null;
    }

    // 게시물 삭제
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 최신 게시물 조회
    public List<Post> getRecentPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    // 인기 게시물 조회 (가장 많은 좋아요 수)
    public List<Post> getPopularPosts() {
        return postRepository.findAllByOrderByLikesDesc();
    }

}
