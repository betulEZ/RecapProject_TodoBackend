package com.example.recapproject_todobackend.service;

import com.example.recapproject_todobackend.model.toDo;
import com.example.recapproject_todobackend.model.toDoStatus;
import com.example.recapproject_todobackend.repository.toDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class toDoService {

    private final IdService idService;
    private final toDoRepository repo;

    public List<toDo> getAll() {
        return repo.findAll();
    }

    public toDo saveNew(toDo todo) {
        toDo temp = todo.withId(idService.generateUUID()).withStatus(toDoStatus.OPEN);
        return repo.save(temp);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }

    public toDo update(String id, toDo toDo) {
        toDo temp=repo.findById(id).orElseThrow();
        temp.setMessage(toDo.getMessage());
        temp.setStatus(toDo.getStatus());
        return repo.save(temp);
    }

    public toDo getById(String id) {
        return repo.findById(id).orElseThrow();
    }
}
