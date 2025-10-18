package com.example.todocrudapi.Service;

import com.example.todocrudapi.Entity.ToDo;
import com.example.todocrudapi.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private TodoRepository todoRepository;

    public ToDo createTodo(ToDo todo) {

        return todoRepository.save(todo);
    }

    public List<ToDo> getAllTodos() {

        return todoRepository.findAll();
    }

    public Optional<ToDo> getTodoById(Long id) {

        return todoRepository.findById(id);
    }

    public ToDo updateTodo(Long id, ToDo newTodoData) {
        return todoRepository.findById(id).map(todo -> {
            todo.setTitle(newTodoData.getTitle());
            todo.setDescription(newTodoData.getDescription());
            todo.setDeadline(newTodoData.getDeadline());
            return todoRepository.save(todo);
        }).orElse(null);
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
