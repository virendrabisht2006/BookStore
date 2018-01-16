package com.abc.store.repository;


import com.abc.store.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByAuthor(@Param("author") String author);

    List<Book> findByTitle(@Param("title") String title);

    Book findById(@PathVariable("id") int id);

    List<Book> findAllBook();
}
