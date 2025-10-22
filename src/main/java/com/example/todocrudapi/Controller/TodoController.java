package com.example.todocrudapi.Controller;

import com.example.todocrudapi.Entity.ToDo;
import com.example.todocrudapi.Service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private ToDoService todoService;


    @PostMapping
    public ResponseEntity<ToDo> createTodo(@Valid @RequestBody ToDo todo) {
        try {
            ToDo createdTodo = todoService.createTodo(todo);
            return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllTodos() {
        List<ToDo> todos = todoService.getAllTodos();
        if (todos != null && !todos.isEmpty()) {
            return new ResponseEntity<>(todos, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        Optional<ToDo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @Valid @RequestBody ToDo todo) {
        ToDo updatedTodo = todoService.updateTodo(id, todo);
        if (updatedTodo != null) {
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        Optional<ToDo> existingTodo = todoService.getTodoById(id);
        if (existingTodo.isPresent()) {
            todoService.deleteTodo(id);
            return new ResponseEntity<>("Todo deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Todo with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
