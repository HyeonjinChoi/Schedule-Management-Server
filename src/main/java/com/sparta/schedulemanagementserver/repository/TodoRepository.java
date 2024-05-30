package com.sparta.schedulemanagementserver.repository;

import com.sparta.schedulemanagementserver.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
