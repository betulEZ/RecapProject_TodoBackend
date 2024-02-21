package com.example.recapproject_todobackend.repository;

import com.example.recapproject_todobackend.model.toDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface toDoRepository extends MongoRepository<toDo, String> {
}
