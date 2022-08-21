package com.indexdisney.indexdisney.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indexdisney.indexdisney.models.Movie;
import com.indexdisney.indexdisney.repositories.MovieRepository;

@Service
public class MovieService {
    
    //Inyección de dependencias
    @Autowired
    MovieRepository movieRepository;

    //Guardar
    public Movie saveMovie(@Valid Movie movie) {
        movieRepository.save(movie);
        return movie;
    }

    //Hacer lista
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }


    //Buscar por ID
    public Movie findId(Long id) {
		return movieRepository.findById(id).get();
	}
    
    /* 
    //Buscar por Nombre
    public Movie findByName(String string) {
		return movieRepository.findByName();
	}
	*/

    //Eliminar por ID
    public void deleteById(long id) {
		movieRepository.deleteById(id);
	}

}
