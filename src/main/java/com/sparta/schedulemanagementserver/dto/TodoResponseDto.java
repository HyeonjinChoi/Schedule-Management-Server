package com.sparta.schedulemanagementserver.dto;

import com.sparta.schedulemanagementserver.entity.Todo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TodoResponseDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
    private LocalDate date;

    public TodoResponseDto(Todo todo){
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.username = todo.getUsername();
        this.contents = todo.getContents();
        this.date = todo.getDate();
    }
}
