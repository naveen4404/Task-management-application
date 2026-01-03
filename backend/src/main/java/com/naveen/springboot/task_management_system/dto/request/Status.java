package com.naveen.springboot.task_management_system.dto.request;

import jakarta.validation.constraints.NotNull;

public class Status {

    @NotNull
    private Boolean done;

    public Status(Boolean done) {
        this.done = done;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
