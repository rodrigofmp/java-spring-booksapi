package com.rodrigofmp.controller;

import com.rodrigofmp.model.Book;
import com.rodrigofmp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/api/books")
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public @ResponseBody
    String addBook (@RequestBody Book bookDto) {
        bookRepository.save(bookDto);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody
    Iterable<Book> getBooks () {
        return bookRepository.findAll();
    }

    @PutMapping(path="{id}")
    public @ResponseBody
    String updateBook (@PathVariable("id") long id, @RequestBody Book bookDto) {
        Optional<Book> opt = bookRepository.findById(id);
        if (opt.isPresent())
        {
            Book book = opt.get();
            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());
            bookRepository.save(book);
            return "Updated";
        }
        else {
            return "Not updated";
        }
    }

    @DeleteMapping(path="{id}")
    public @ResponseBody
    String deleteBook (@PathVariable("id") long id) {
        Optional<Book> opt = bookRepository.findById(id);
        if (opt.isPresent())
        {
            bookRepository.delete(opt.get());
            return "Deleted";
        }
        else {
            return "Not deleted";
        }
    }

}
