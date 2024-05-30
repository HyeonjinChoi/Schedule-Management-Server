package com.sparta.schedulemanagementserver.entity;

import com.sparta.schedulemanagementserver.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "todo", nullable = false)
    private String todo;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public Comment(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
        this.todo = commentRequestDto.getTodo();
        this.username = commentRequestDto.getUsername();
        this.date = LocalDate.now();
    }

    public void update(CommentRequestDto commentRequestDto) {
        this.contents = commentRequestDto.getContents();
        this.todo = commentRequestDto.getTodo();
        this.username = commentRequestDto.getUsername();
        this.date = LocalDate.now();
    }
}
