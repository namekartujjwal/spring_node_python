package com.example.first;

import jakarta.persistence.*;

@Entity 
@Table (indexes = @Index (name = "idx_book_title", columnList = "title"))
public class Book {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ManyToOne (fetch = FetchType.LAZY)
    private Author author;

    public Long getId(){
        return id;

    }
    public void setId(Long id){
        this.id = id;
    }
    public String getTite(){
        return title;
    }
    public void setTite(String title){
        this.title = title;
    }
    public Author getAuthor(){
        return author;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
}
