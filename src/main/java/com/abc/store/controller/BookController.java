package com.abc.store.controller;


import com.abc.store.model.Book;
import com.abc.store.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping(value = "/v1/books")
@Api(value = "/v1/books", description = "The api manage the book store, and would be used for crud operation on book object")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "/v1/books", notes = "Save book into db", responseContainer = "Return HTTP 201 code on success and URI for added task")
    public ResponseEntity<?> saveBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        System.out.println("About to save book for title: " + book.getTitle());
        Book book1 = bookService.save(book);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/books/{id}").buildAndExpand(book1.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "/v1/books/id", notes = "Taskes id as input and delete the book from db for book id", responseContainer = "Return HTTP 204 for on success")
    public ResponseEntity<?> deleteBook(@PathVariable("id") int id) {
        System.out.println("About to delete Book for id: " + id);
        bookService.deleteBook(id);
        return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "/v1/books/id", notes = "Taskes id as input and query the book from db", responseContainer = "Return HTTP 204 for on success")
    public ResponseEntity<?> queryBook(@PathVariable("id") int id) {
        System.out.println("About to query Book for id: " + id);
        Book book = bookService.findById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }
}
