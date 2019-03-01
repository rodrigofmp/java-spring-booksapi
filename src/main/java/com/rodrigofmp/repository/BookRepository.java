package com.rodrigofmp.repository;

import com.rodrigofmp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}