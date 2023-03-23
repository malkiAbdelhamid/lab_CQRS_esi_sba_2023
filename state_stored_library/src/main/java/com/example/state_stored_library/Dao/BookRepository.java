package com.example.state_stored_library.Dao;

import com.example.state_stored_library.Aggregate.BookAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookAggregate,String> {
}
