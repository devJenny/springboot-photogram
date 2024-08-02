package com.example.photogram.service;

import com.example.photogram.domain.comment.Comment;
import com.example.photogram.domain.comment.CommentRepository;
import com.example.photogram.domain.image.Image;
import com.example.photogram.domain.user.User;
import com.example.photogram.domain.user.UserRepository;
import com.example.photogram.handler.ex.CustomApiException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment createComment(String content, int imageId, int userId) {

        // TIP (객체를 만들 때 id값만 담아서 insert 할 수 있다)
        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomApiException("유저 아이디를 찾을 수 없습니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(int id) {
        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }

}
