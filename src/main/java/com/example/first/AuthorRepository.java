package com.example.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long>{
    List<Author> findAll();
    @Query ("SELECT a FROM Author a JOIN FETCH a.books")
    List<Author> findAllWithBooks();
}
