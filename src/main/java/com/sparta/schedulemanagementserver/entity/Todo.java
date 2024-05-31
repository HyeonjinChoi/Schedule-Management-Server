package com.sparta.schedulemanagementserver.entity;

import com.sparta.schedulemanagementserver.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "todo")
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @OneToMany(mappedBy = "todo")
    private List<Comment> commentList = new ArrayList<>();

    public Todo(TodoRequestDto todoRequestDto) {
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.username = todoRequestDto.getUsername();
        this.password = todoRequestDto.getPassword();
        this.date = LocalDate.now();
    }

    public void update(TodoRequestDto todoRequestDto) {
        this.title = todoRequestDto.getTitle();
        this.contents = todoRequestDto.getContents();
        this.username = todoRequestDto.getUsername();
        this.password = todoRequestDto.getPassword();
        this.date = LocalDate.now();
    }
}
