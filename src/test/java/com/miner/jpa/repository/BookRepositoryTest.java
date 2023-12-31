package com.miner.jpa.repository;

import com.miner.jpa.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("JPA Book");
        book.setAuthor("Me");

        bookRepository.save(book);
        bookRepository.findAll().forEach(System.out::println);
    }
}