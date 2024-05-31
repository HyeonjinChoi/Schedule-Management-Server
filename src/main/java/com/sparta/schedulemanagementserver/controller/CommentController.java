package com.sparta.schedulemanagementserver.controller;

import com.sparta.schedulemanagementserver.dto.CommentRequestDto;
import com.sparta.schedulemanagementserver.dto.CommentResponseDto;
import com.sparta.schedulemanagementserver.entity.Comment;
import com.sparta.schedulemanagementserver.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{todoId}/comment")
    public CommentResponseDto createComment(@PathVariable long todoId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(todoId, requestDto);
    }
}
