package com.abc.store.service;


import com.abc.store.model.Book;
import com.abc.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAllBook() {
        return bookRepository.findAllBook();
    }

    public void deleteBook(int id) {
        bookRepository.delete(id);
    }

    public Book findById(int id) {
        return bookRepository.findById(id);
    }


}
