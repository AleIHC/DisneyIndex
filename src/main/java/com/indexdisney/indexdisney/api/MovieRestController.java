package com.indexdisney.indexdisney.api;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.indexdisney.indexdisney.models.DisneyCharacter;
import com.indexdisney.indexdisney.models.Genre;
import com.indexdisney.indexdisney.models.Movie;
import com.indexdisney.indexdisney.repositories.DisneyCharacterRepository;
import com.indexdisney.indexdisney.repositories.GenreRepository;
import com.indexdisney.indexdisney.repositories.MovieRepository;
import com.indexdisney.indexdisney.services.DisneyCharacterService;
import com.indexdisney.indexdisney.services.GenreService;
import com.indexdisney.indexdisney.services.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {
    
    //Inyección de dependencias
    @Autowired
    DisneyCharacterService disneyCharacterService;

    @Autowired
    DisneyCharacterRepository disneyCharacterRepository;

    @Autowired
    MovieService movieService;
    
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    GenreService genreService;

    @Autowired
    GenreRepository genreRepository;

    //Guardar película
    @PostMapping 
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie){

        //Creando opcional para validación del género
		Optional<Genre> genreOptional = genreRepository.findById(movie.getGenre().getGenreId());
		if(!genreOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
        //Asignarle el género y guardar en repositorio
		movie.setGenre(genreOptional.get());
		Movie savedMovie = movieService.saveMovie(movie);
		return ResponseEntity.ok(savedMovie);
	}

    //Obtener lista de películas
    @GetMapping
    public ResponseEntity<List<Movie>> moviesList() {
        return ResponseEntity.ok(movieService.findAll());
    }

    //Obtener película por ID
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {

        //Creando opcional para validación de película si el ID no está
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(!movieOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(movieOptional.get());
    }

    //Obtener película por nombre
    @RequestMapping("/movies")
    public ResponseEntity<Collection<Movie>> getMovieByName(@RequestParam(value="name") String name) {
        Collection<Movie> moviesByName = movieRepository.findByName(name);

        return ResponseEntity.ok(moviesByName);
    }


    //Editar
    @PutMapping("/{id}")
	public ResponseEntity<Movie> movieUpdate(@Valid @RequestBody Movie movie, @PathVariable Long id){

        //Opcional de la película para validación
		Optional<Movie> movieOptional = movieRepository.findById(id);
		if(!movieOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
        //Cambiando género e ID de la película actual
		movie.setMovieId(movieOptional.get().getMovieId());
		movieRepository.save(movie);
		return ResponseEntity.ok(movieOptional.get());
	}

    //Borrar
    @DeleteMapping("/{id}")
	public ResponseEntity<Movie> deleteMovie(@PathVariable Long id){

        //Creando opcional de película y verificando si existe en repositorio
		Optional<Movie> movieOptional = movieRepository.findById(id);
		if(!movieOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
        //Borrar en repositorio 
		movieRepository.delete(movieOptional.get());
		return ResponseEntity.noContent().build();
	}


    //Agregar personaje a película
    @PostMapping("/{idMovie}/characters/{id}")
	public ResponseEntity<DisneyCharacter> newMovieDisneyCharacter(@PathVariable(value = "idMovie") Long idMovie, @PathVariable(value = "id") Long id, @Valid @RequestBody DisneyCharacter disneyCharacter) {
        //Película del ID
        Movie movieToAddCharacter = movieService.findId(idMovie);

        //Lista de personajes de la película
        List<DisneyCharacter> movieDisneyCharacters = movieToAddCharacter.getCharacters();

        //Darle el otro ID al personaje
        disneyCharacter.setCharacterId(id);

        
        //Agregar nuevo personaje a lista de personajes de la película
        movieDisneyCharacters.add(disneyCharacter);

        //Guardar nueva lista de personajes en la película
        movieToAddCharacter.setCharacters(movieDisneyCharacters);
        movieRepository.save(movieToAddCharacter);
        disneyCharacterRepository.save(disneyCharacter);
		return ResponseEntity.ok(disneyCharacter);
	}


    //Borrar personaje de película
    @DeleteMapping("/{idMovie}/characters/{id}")
	public ResponseEntity<DisneyCharacter> deleteMovieDisneyCharacter(@PathVariable(value = "idMovie") Long idMovie, @PathVariable(value = "id") Long id, DisneyCharacter disneyCharacter) {
        //Película del ID
        Optional<Movie> movieOptional = movieRepository.findById(idMovie);
        if(!movieOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}

        //Personaje del ID
        Optional<DisneyCharacter> disneyCharacterOptional = disneyCharacterRepository.findById(id);
        if(!disneyCharacterOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}

        disneyCharacter.setCharacterId(disneyCharacterOptional.get().getCharacterId());

        //Lista de personajes de la película
        List<DisneyCharacter> movieDisneyCharacters = movieOptional.get().getCharacters();

        //Borrar personaje de lista de personajes de la película
        movieDisneyCharacters.remove(disneyCharacter);

        //Borrar personaje
        disneyCharacterRepository.delete(disneyCharacter);
		return ResponseEntity.noContent().build();
	}
}
