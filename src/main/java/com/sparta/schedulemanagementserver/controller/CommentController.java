package com.sparta.schedulemanagementserver.controller;

import com.sparta.schedulemanagementserver.dto.CommentRequestDto;
import com.sparta.schedulemanagementserver.dto.CommentResponseDto;
import com.sparta.schedulemanagementserver.entity.Comment;
import com.sparta.schedulemanagementserver.service.CommentService;
import com.sparta.schedulemanagementserver.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class CommentController {

    private final CommentService commentService;
    private final TodoService todoService;

    @PostMapping("/{todoId}/comment")
    public CommentResponseDto createComment(@PathVariable long todoId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(todoId, requestDto);
    }

    @PutMapping("/{todoId}/comment/{commentId}")
    public Long updateComment(@PathVariable long todoId, @PathVariable long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(todoId, commentId, requestDto);
    }

    @DeleteMapping("/{todoId}/comment/{commentId}")
    public Long deleteComment(@PathVariable long todoId, @PathVariable long commentId) {
        return commentService.deleteComment(todoId, commentId);
    }
}
