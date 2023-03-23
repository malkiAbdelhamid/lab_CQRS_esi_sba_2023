package com.example.state_stored_library.Aggregate;

import com.example.coreapi.commands.AddBookCommand;
import com.example.coreapi.commands.LibraryCreationCommand;
import com.example.coreapi.commands.RemoveBookCommand;
import com.example.coreapi.events.BookAddedEvent;
import com.example.coreapi.events.BookRemovedEvent;
import com.example.coreapi.events.LibraryCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Aggregate
@NoArgsConstructor
@Entity @Data @AllArgsConstructor
public class LibraryAggregate {
     @AggregateIdentifier
     @Id
    private String libraryId;
    private String name;

    @OneToMany(mappedBy = "library")
    @AggregateMember
    private List<BookAggregate> Books;

    @CommandHandler
    public LibraryAggregate(LibraryCreationCommand cmd){
        Assert.notNull(cmd.getLibraryId(),"LibraryId should be not null");
        Assert.notNull(cmd.getName(), "name should be not null");
        AggregateLifecycle.apply(new LibraryCreatedEvent(cmd.getLibraryId(), cmd.getName()));
    }
    @EventSourcingHandler
    public  void on (LibraryCreatedEvent event)    {
        this.libraryId=event.getLibraryId();
        this.name=event.getName();
        this.Books=new ArrayList<>();
    }

    @CommandHandler
    public void handles(AddBookCommand cmd) throws  Exception{
        Assert.notNull(cmd.getLibraryId(),"LibraryId should be not null");
        Assert.notNull(cmd.getIsbn(),"ISBN should be not null");

        for(BookAggregate book: this.Books)
            if(book.getIsbn().equals(cmd.getIsbn()))
                 throw new Exception("Book ISBN must be unique");

        AggregateLifecycle.apply(new BookAddedEvent(cmd.getLibraryId(), cmd.getIsbn(), cmd.getTitle(),
                cmd.getEditeurId()));
    }

    @EventSourcingHandler
    public void on (BookAddedEvent event){
        this.Books.add(new BookAggregate(event.getIsbn(), event.getTitle(),
                                        event.getEditeurId(),this));
    }

    @CommandHandler
    public void handler(RemoveBookCommand cmd) throws Exception {
        Assert.notNull(cmd.getLibraryId(),"LibraryId should be not null");
        Assert.notNull(cmd.getIsbn(),"ISBN should be not null");



        boolean exist=false;
        for(BookAggregate book: this.Books)
        {
            if(book.getIsbn().equals(cmd.getIsbn()))
            {
                exist=true;
                break;
            }
        }

        if(!exist) throw new Exception("Book ISBN must be existed");
        AggregateLifecycle.apply(new BookRemovedEvent(cmd.getLibraryId(),cmd.getIsbn()));
    }

    @EventSourcingHandler
    public void on (BookRemovedEvent event )
    {

        for(BookAggregate book: this.Books)
        {
            if(book.getIsbn().equals(event.getIsbn()))
            {
                this.Books.remove(book);

                break;
            }
        }
    }
}
