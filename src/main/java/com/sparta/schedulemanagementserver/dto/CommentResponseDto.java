package com.sparta.schedulemanagementserver.dto;

import com.sparta.schedulemanagementserver.entity.Comment;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {
    private Long id;
    private String contents;
    private String username;
    private LocalDate date;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.contents = comment.getContents();
        this.username = comment.getUsername();
        this.date = comment.getDate();
    }
}
