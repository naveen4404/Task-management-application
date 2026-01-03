package com.naveen.springboot.task_management_system.dto.mapper;

import com.naveen.springboot.task_management_system.dto.request.TodoRequestDto;
import com.naveen.springboot.task_management_system.dto.response.TodoResponseDto;
import com.naveen.springboot.task_management_system.entity.Todo;

public class TodoMapper {

    public static Todo toEntity(TodoRequestDto requestDto){

        Todo todo = new Todo();
        todo.setDescription(requestDto.getDescription());
        todo.setTargetDate(requestDto.getTargetDate());
        todo.setDone(requestDto.getDone());
        return todo;
    }

    public static TodoResponseDto toResponse(Todo todo){
        return new TodoResponseDto(todo.getId(), todo.getDescription(),todo.getTargetDate(),todo.isDone());
    }
}
