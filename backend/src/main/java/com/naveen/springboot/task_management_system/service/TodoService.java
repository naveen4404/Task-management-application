package com.naveen.springboot.task_management_system.service;

import com.naveen.springboot.task_management_system.dto.mapper.TodoMapper;
import com.naveen.springboot.task_management_system.dto.request.TodoRequestDto;
import com.naveen.springboot.task_management_system.dto.response.TodoResponseDto;
import com.naveen.springboot.task_management_system.entity.Todo;
import com.naveen.springboot.task_management_system.entity.User;
import com.naveen.springboot.task_management_system.exception.ResourceNotFoundException;
import com.naveen.springboot.task_management_system.repository.TodoRepository;
import com.naveen.springboot.task_management_system.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public List<TodoResponseDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(TodoMapper::toResponse).toList();
    }

    public List<TodoResponseDto> getAllTodosByUserEmail(String email) {
        List<Todo> todos = todoRepository.findTodoByUser_Email(email);
        return todos.stream().map(TodoMapper::toResponse).toList();
    }

    public TodoResponseDto getTodoById(Integer id) {

        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isEmpty()) {
            throw new ResourceNotFoundException("Todo with the id: " + id + " is not exist");
        }
        return TodoMapper.toResponse(todo.get());
    }

    public TodoResponseDto save(TodoRequestDto todo, String userEmail) {
        Todo todoToSave = TodoMapper.toEntity(todo);
        Optional<User> user = userRepository.findUserByEmail(userEmail);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        todoToSave.setUser(user.get());
        todoRepository.save(todoToSave);
        return TodoMapper.toResponse(todoToSave);
    }

    public void deleteTodoById(Integer id) {

        // 1) This approach is not recommended
        // - causes race condition
        // - cascade rules may not fire
        // - child rows may remains
        // if(!todoRepository.existsById(id)) throw new ResourceNotFoundException("Todo
        // with the id: "+id+" is not exist");
        // todoRepository.deleteById(id);

        // 2)

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo with the id:" + id + " is not exist"));
        todoRepository.delete(todo);
    }

    public TodoResponseDto updateTodoById(Integer id, TodoRequestDto todo) {
        Todo oldTodo = todoRepository.findById(id).orElse(null);
        if (oldTodo == null)
            throw new ResourceNotFoundException("Todo with the id: " + id + " is not exist");
        oldTodo.setDescription(todo.getDescription());
        oldTodo.setTargetDate(todo.getTargetDate());
        oldTodo.setDone(todo.getDone());
        Todo updatedTodo = todoRepository.save(oldTodo);
        return TodoMapper.toResponse(updatedTodo);
    }

    public TodoResponseDto markTodo(Integer id, Boolean done) {
        Todo oldTodo = todoRepository.findById(id).orElse(null);
        if (oldTodo == null)
            throw new ResourceNotFoundException("Todo with the id: " + id + " is not exist");
        oldTodo.setDone(done);
        Todo updatedTodo = todoRepository.save(oldTodo);
        return TodoMapper.toResponse(updatedTodo);
    }
}
