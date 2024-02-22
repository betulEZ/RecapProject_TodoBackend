package com.example.recapproject_todobackend.controller;

import com.example.recapproject_todobackend.model.toDo;
import com.example.recapproject_todobackend.service.toDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class toDoController {

    private final toDoService service;

    @GetMapping
    public List<toDo> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public toDo getById(@PathVariable String id){
        return service.getById(id);
    }

    @PostMapping
    public toDo saveNew(@RequestBody toDo todo){
        return service.saveNew(todo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
       service.delete(id);
    }

    @PutMapping("/{id}")
    public toDo update(@PathVariable String id,@RequestBody toDo toDo){
        return service.update(id,toDo);
    }
}
