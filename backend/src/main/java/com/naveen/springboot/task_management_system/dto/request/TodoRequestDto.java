package com.naveen.springboot.task_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TodoRequestDto {

    @Size(min = 3, max = 100)
    private String description;

    @NotNull
    private LocalDate targetDate;

    @NotNull
    private Boolean done;

    public TodoRequestDto(String description, LocalDate targetDate, Boolean done) {
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
