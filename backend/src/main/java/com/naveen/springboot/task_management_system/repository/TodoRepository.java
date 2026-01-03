package com.naveen.springboot.task_management_system.repository;

import com.naveen.springboot.task_management_system.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Integer> {

    List<Todo> findTodoByUser_Email(String userEmail);
}
