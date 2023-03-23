package com.example.state_stored_library.projection;

import com.example.coreapi.events.BookAddedEvent;
import com.example.coreapi.events.BookRemovedEvent;
import com.example.coreapi.events.LibraryCreatedEvent;

import com.example.state_stored_library.Aggregate.BookAggregate;
import com.example.state_stored_library.Aggregate.LibraryAggregate;
import com.example.state_stored_library.Dao.BookRepository;
import com.example.state_stored_library.Dao.LibraryRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryProjection {
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void  AddLibrary(LibraryCreatedEvent event)
    {
        LibraryAggregate library=new LibraryAggregate(event.getLibraryId(), event.getName(), null);
        libraryRepository.save(library);
    }

    @EventHandler
    public void addBook(BookAddedEvent event){
        LibraryAggregate library=libraryRepository.findById(event.getLibraryId()).get();

      bookRepository.save(                new BookAggregate(event.getIsbn(), event.getTitle(), event.getEditeurId(),library ));
    }

    @EventHandler
    public void removebook(BookRemovedEvent event) {

        bookRepository.deleteById(event.getIsbn());
    }
}
