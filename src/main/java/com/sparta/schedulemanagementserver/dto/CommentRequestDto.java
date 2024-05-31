package com.sparta.schedulemanagementserver.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;
    private String username;
}
