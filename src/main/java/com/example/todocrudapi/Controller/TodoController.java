package com.example.todocrudapi.Controller;

import com.example.todocrudapi.Entity.ToDo;
import com.example.todocrudapi.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private ToDoService todoService;

    @PostMapping
    public ToDo createTodo(@RequestBody ToDo todo) {
        return todoService.createTodo(todo);
    }

    @GetMapping
    public List<ToDo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ToDo getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ToDo updateTodo(@PathVariable Long id, @RequestBody ToDo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "Todo deleted successfully!";
    }
}
