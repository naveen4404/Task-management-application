package com.naveen.springboot.task_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    // @Column(nullable = false)
    // @Size(min = 3)
    // private String userName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    // @Column(nullable = false, updatable = false)
    // private LocalDateTime createdAt;
    //
    // @Column(nullable = false)
    // private LocalDateTime updatedAt;
    //
    // @Column(nullable = false)
    // private Boolean enabled;
    //
    // @Column(nullable = false)
    // private Boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Todo> todos;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", todos=" + todos +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
