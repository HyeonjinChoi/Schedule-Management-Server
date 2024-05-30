package com.sparta.schedulemanagementserver.controller;

import com.sparta.schedulemanagementserver.dto.TodoRequestDto;
import com.sparta.schedulemanagementserver.dto.TodoResponseDto;
import com.sparta.schedulemanagementserver.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todo")
    public List<TodoResponseDto> getAllTodo() {
        return todoService.getAllTodo();
    }

    @GetMapping("/todo/{id}")
    public TodoResponseDto getTodo(@PathVariable long id) {
        return todoService.getTodo(id);
    }

    @PutMapping("/todo/{id}")
    public Long updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    @DeleteMapping("/todo/{id}")
    public Long deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }
}
