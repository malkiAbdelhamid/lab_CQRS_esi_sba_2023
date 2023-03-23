package com.example.state_stored_library.Aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.EntityId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class BookAggregate {

    @Id
    @EntityId
    private String isbn;


    private String title;
    private String editeurId;
    @ManyToOne
    private LibraryAggregate library;
}
