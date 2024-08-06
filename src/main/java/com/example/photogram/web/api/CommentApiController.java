package com.example.photogram.web.api;

import com.example.photogram.config.auth.PrincipalDetails;
import com.example.photogram.domain.comment.Comment;
import com.example.photogram.handler.ex.CustomValidationApiException;
import com.example.photogram.service.CommentService;
import com.example.photogram.web.dto.CMResDto;
import com.example.photogram.web.dto.comment.CommentDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/comment")
    public ResponseEntity<?> saveComment(@Valid @RequestBody CommentDto commentDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Comment comment = commentService.createComment(commentDto.getContent(), commentDto.getImageId(), principalDetails.getUser().getId());// content, imageId, userId

        return new ResponseEntity<>(new CMResDto<>(1, "댓글쓰기성공", comment), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") int id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(new CMResDto<>(1, "댓글삭제성공", null), HttpStatus.OK);

    }
}
