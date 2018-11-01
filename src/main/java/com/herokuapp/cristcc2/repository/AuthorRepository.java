package com.herokuapp.cristcc2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.herokuapp.cristcc2.Models.Author;



public interface AuthorRepository extends CrudRepository<Author, Long> {

	List<Author> findAllByOrderByLastNameAscFirstNameAsc();

}
