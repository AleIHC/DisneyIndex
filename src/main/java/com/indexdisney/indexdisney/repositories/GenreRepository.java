package com.indexdisney.indexdisney.repositories;

import com.indexdisney.indexdisney.models.Genre;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {


    //Query para buscar por nombre
    @Query(value="SELECT * from genres g WHERE g.genreName = ?1", nativeQuery = true)
	Genre findByName(String name);
}