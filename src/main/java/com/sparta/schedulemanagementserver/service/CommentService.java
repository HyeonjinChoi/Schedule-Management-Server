package com.sparta.schedulemanagementserver.service;

import com.sparta.schedulemanagementserver.dto.CommentRequestDto;
import com.sparta.schedulemanagementserver.dto.CommentResponseDto;
import com.sparta.schedulemanagementserver.entity.Comment;
import com.sparta.schedulemanagementserver.entity.Todo;
import com.sparta.schedulemanagementserver.repository.CommentRepository;
import com.sparta.schedulemanagementserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(Long todoId, CommentRequestDto requestDto) {
        // 예외 처리: 선택한 일정의 ID를 입력 받지 않은 경우
        if (todoId == null) {
            throw new IllegalArgumentException("선택한 일정의 ID를 입력 받지 않았습니다.");
        }

        // RequestDto -> Entity
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        Comment comment = new Comment(requestDto, todoOptional.get());

        // 예외 처리: 댓글 내용이 비어 있는 경우
        if (comment.getContents() == null) {
            throw new IllegalArgumentException("댓글 내용이 비어 있습니다.");
        }

        // 예외 처리: 일정이 DB에 저장되지 않은 경우
        if (todoOptional.isEmpty()) {
            throw new IllegalArgumentException("일정이 DB에 저장되지 않았습니다.");
        }

        // DB 저장
        Comment savedComment = commentRepository.save(comment);

        // Entity -> ResponseDto
        CommentResponseDto commentResponseDto = new CommentResponseDto(savedComment);

        return commentResponseDto;
    }

    @Transactional
    public Long updateComment(Long todoId, Long commentId, CommentRequestDto requestDto) {
        // 예외 처리: 선택한 일정의 ID나 댓글의 ID를 입력 받지 않은 경우
        if (todoId == null || commentId == null) {
            throw new IllegalArgumentException("선택한 일정이나 댓글의 ID를 입력 받지 않았습니다.");
        }

        // 예외 처리: 일정이 DB에 저장되지 않은 경우
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if (todoOptional.isEmpty()) {
            throw new IllegalArgumentException("일정이 DB에 저장되지 않았습니다.");
        }

        // 예외 처리: 댓글이 DB에 저장되지 않은 경우
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (!commentOptional.isPresent()) {
            throw new IllegalArgumentException("댓글이 DB에 저장되지 않았습니다.");
        }

        // 댓글 수정
        Comment comment = findComment(commentId);
        comment.update(requestDto);

        return commentId;
    }

    public Long deleteComment(Long todoId, Long commentId) {
        // 예외 처리: 선택한 일정의 ID나 댓글의 ID를 입력 받지 않은 경우
        if (todoId == null || commentId == null) {
            throw new IllegalArgumentException("선택한 일정이나 댓글의 ID를 입력 받지 않았습니다.");
        }

        // 예외 처리: 일정이 DB에 저장되지 않은 경우
        Optional<Todo> todoOptional = todoRepository.findById(todoId);
        if (todoOptional.isEmpty()) {
            throw new IllegalArgumentException("일정이 DB에 저장되지 않았습니다.");
        }

        // 예외 처리: 댓글이 DB에 저장되지 않은 경우
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isEmpty()) {
            throw new IllegalArgumentException("댓글이 DB에 저장되지 않았습니다.");
        }

        // 댓글 삭제
        commentRepository.delete(findComment(commentId));

        return commentId;
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }
}
