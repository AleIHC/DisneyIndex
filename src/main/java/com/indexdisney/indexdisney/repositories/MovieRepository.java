package com.indexdisney.indexdisney.repositories;

import com.indexdisney.indexdisney.models.Movie;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    //Query para buscar por nombre
    @Query(value="SELECT * from movies WHERE movie_title = ?1", nativeQuery = true)
	Collection<Movie> findByName(String name);
}