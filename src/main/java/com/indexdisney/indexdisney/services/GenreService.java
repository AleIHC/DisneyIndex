package com.indexdisney.indexdisney.services;


import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indexdisney.indexdisney.models.Genre;
import com.indexdisney.indexdisney.repositories.GenreRepository;

@Service
public class GenreService {
    
    //Inyección de dependencias
    @Autowired
    GenreRepository genreRepository;


    //Guardar
    public Genre saveGenre(Genre genre) {
        genreRepository.save(genre);
        return genre;
    }

    //Buscar por ID
    public Genre findId(Long id) {
		return genreRepository.findById(id).get();
	}

    //Buscar por nombre
    public Genre findByName(String name) {
        return genreRepository.findByName(name);
    }


    //Hacer lista
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }
}
