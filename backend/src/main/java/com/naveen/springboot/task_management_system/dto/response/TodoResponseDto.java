package com.naveen.springboot.task_management_system.dto.response;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TodoResponseDto {

    private Integer id;

    @Size(min = 8)
    private String description;

    private LocalDate targetDate;
    private Boolean done;

    public TodoResponseDto() {

    }

    public TodoResponseDto(Integer id, String description, LocalDate targetDate,Boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
        this.targetDate = targetDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
