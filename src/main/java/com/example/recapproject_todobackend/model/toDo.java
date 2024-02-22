package com.example.recapproject_todobackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Document("toDo")
public class toDo {

    private String id;
    private String description;
    private toDoStatus status;
}
