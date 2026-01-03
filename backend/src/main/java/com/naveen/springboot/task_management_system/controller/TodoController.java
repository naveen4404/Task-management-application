package com.naveen.springboot.task_management_system.controller;
import com.naveen.springboot.task_management_system.dto.request.Status;
import com.naveen.springboot.task_management_system.dto.request.TodoRequestDto;
import com.naveen.springboot.task_management_system.dto.response.TodoResponseDto;
import com.naveen.springboot.task_management_system.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping()
    public ResponseEntity<List<TodoResponseDto>> retrieveTodos( @RequestHeader("useremail") String userEmail){
        List<TodoResponseDto> todos = todoService.getAllTodosByUserEmail(userEmail);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Integer id) throws Exception{
        TodoResponseDto todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    @PostMapping()
    public ResponseEntity<TodoResponseDto> addTodo(@Valid @RequestBody TodoRequestDto todo, @RequestHeader("useremail") String userEmail){
        TodoResponseDto savedTodo = todoService.save(todo,userEmail);
//      URI location = URI.create("/api/v1/todos/"+savedTodo.getId());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTodo.getId()).toUri();
        return ResponseEntity.created(location).body(savedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id){
        todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Integer id, @RequestBody @Valid TodoRequestDto todo){
        TodoResponseDto updatedTodo = todoService.updateTodoById(id, todo);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/mark/{id}")
    public ResponseEntity<TodoResponseDto> markTodo(@PathVariable Integer id, @Valid @RequestBody Status status){
        TodoResponseDto updatedTodo = todoService.markTodo(id, status.getDone());
        return ResponseEntity.ok(updatedTodo);
    }

}
