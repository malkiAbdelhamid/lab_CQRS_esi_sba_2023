package com.example.state_stored_library.Dao;

import com.example.state_stored_library.Aggregate.LibraryAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<LibraryAggregate,String> {
}
