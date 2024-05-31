package com.sparta.schedulemanagementserver.service;

import com.sparta.schedulemanagementserver.dto.TodoRequestDto;
import com.sparta.schedulemanagementserver.dto.TodoResponseDto;
import com.sparta.schedulemanagementserver.entity.Todo;
import com.sparta.schedulemanagementserver.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        // RequestDto -> Entity
        Todo todo = new Todo(requestDto);

        // DB 저장
        Todo saveTodo = todoRepository.save(todo);

        // Entity -> ResponseDto
        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);

        return todoResponseDto;
    }

    public List<TodoResponseDto> getAllTodo() {
        // DB 조회
        return todoRepository.findAll().stream().map(TodoResponseDto::new).toList();
    }

    public TodoResponseDto getTodo(long id) {
        // DB 조회
        return new TodoResponseDto(findTodo(id));
    }

    @Transactional
    public Long updateTodo(Long id, TodoRequestDto requestDto) {
        // 일정 수정
        Todo todo = findTodo(id);
        todo.update(requestDto);

        return id;
    }

    public Long deleteTodo(Long id) {
        // 일정 삭제
        todoRepository.delete(findTodo(id));

        return id;
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }
}
