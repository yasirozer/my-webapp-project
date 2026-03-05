package com.example.todo.controller;

import com.example.todo.repository.TodoRepository;
import com.example.todo.database.DatabaseInitializer;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API controller for managing todos.
 * Provides endpoints for CRUD operations on todo items.
 */
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    // Initialize database on controller load
    static {
        DatabaseInitializer.initialize();
    }

    private final TodoRepository todoRepository;

    /** Constructor that initializes the TodoRepository. */
    public TodoController() {
        this.todoRepository = new TodoRepository();
    }

    /**
     * Retrieves all todos.
     *
     * @return list of all todo strings
     */
    @GetMapping
    public List<String> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    /**
     * Adds a new todo item.
     *
     * @param task the todo task description
     */
    @PostMapping
    public void addTodo(@RequestBody String task) {
        todoRepository.addTodo(task);
    }

    /**
     * Deletes a todo item by ID.
     *
     * @param id the ID of the todo to delete
     */
    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable int id) {
        todoRepository.deleteTodoById(id);
    }
}