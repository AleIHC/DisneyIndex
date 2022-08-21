package com.indexdisney.indexdisney.api;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.indexdisney.indexdisney.models.Genre;

import com.indexdisney.indexdisney.services.GenreService;
import com.indexdisney.indexdisney.services.MovieService;


@RestController
@RequestMapping("/api/genre")
public class GenreRestController {
    
    //Inyección de dependencias
    @Autowired
    MovieService movieService;

    @Autowired
    GenreService genreService;


    //Guardar género
    @PostMapping
    public ResponseEntity<Genre> saveGenre(@Valid @RequestBody Genre genre) {
        Genre savedGenre = genreService.saveGenre(genre);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedGenre.getGenreId()).toUri();
        return ResponseEntity.created(location).body(savedGenre);
    }

    //Lista de géneros
    @GetMapping
    public ResponseEntity<List<Genre>> genreList() {
        return ResponseEntity.ok(genreService.findAll());
    }

}
