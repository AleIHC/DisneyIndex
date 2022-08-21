package com.indexdisney.indexdisney.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.indexdisney.indexdisney.models.DisneyCharacter;
import com.indexdisney.indexdisney.models.Movie;
import com.indexdisney.indexdisney.services.DisneyCharacterService;
import com.indexdisney.indexdisney.services.GenreService;
import com.indexdisney.indexdisney.services.MovieService;

@Controller
@RequestMapping("/movies")
public class MovieController {
    
    //Inyección de dependencias
    @Autowired
    MovieService movieService;

    @Autowired
    GenreService genreService;

    @Autowired
    DisneyCharacterService disneyCharacterService;

        
        //Mostrar películas
        @RequestMapping("")
        public String movies(@ModelAttribute("movie") Movie movie,Model model) {

            //Lista de películas disponibles
            List<Movie> moviesList = movieService.findAll();
            List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

            //Lista de películas y personajes a showmovies.jsp
            model.addAttribute("savedCharacters", disneyCharacterList);
			model.addAttribute("savedMovies", moviesList);
            return "showmovies.jsp";
        }

        //Ruta para nueva película
        @RequestMapping("/new")
        public String newMovie(@ModelAttribute("movie") Movie movie){
            return "newmovie.jsp"; 
        }

        /***GUARDAR***/
        //Guardar pelicula
        @PostMapping("/save")
		public String saveNewMovie(@ModelAttribute("movie") Movie movie, 
		BindingResult result, Model model) {

            if (result.hasErrors()) {
				model.addAttribute("msgError", "Debe ingresar los datos de la pelicula");
				return "newmovie.jsp";
			} else {
				//Pelicula al service
				movieService.saveMovie(movie);

				//Lista de personajes y películas
				List<Movie> moviesList = movieService.findAll();
				List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

				//Lista de peliculas y personajes a showmovies.jsp
				model.addAttribute("savedCharacters", disneyCharacterList);
				model.addAttribute("savedMovies", moviesList);
				return "redirect:/movies";
            }
		}

        /***EDICION**/
        //Editar para despliegue de todas las peliculas
        @RequestMapping("/edit/{id}")
		public String editMovie(@PathVariable("id") Long id, Model model) {

            //Buscar por ID en service y redireccionar para la edicion
			Movie movie = movieService.findId(id);
			model.addAttribute("movie", movie);
			return "editmovie.jsp";
		}

        //Update que persista en DB
        @PostMapping("/update/{id}")
		public String updateMovie(@PathVariable("id") Long id ,@Valid @ModelAttribute("movie") Movie movie,
        BindingResult result, Model model) {

			//Si existe un error
			if (result.hasErrors()) {
				model.addAttribute("msgError", "Debe ingresar los datos de la película correctamente");
				return "editmovie.jsp";
			} else {
				//Pelicula al service
				movieService.saveMovie(movie);

				//Lista de peliculas
				List<Movie> moviesList = movieService.findAll();
                List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

				//Lista de películas y personajes a showmovies.jsp
				model.addAttribute("savedCharacters", disneyCharacterList);
                model.addAttribute("savedMovies", moviesList);
				return "redirect:/movies";
			}
		}

        /***ELIMINACIÓN***/
        //Eliminar película
		@RequestMapping("/delete/{id}")
		public String deleteMovie (@PathVariable("id")long id) {

            //Eliminar por id
            movieService.deleteById(id);

            //Redireccionar a showmovies.jsp
            return "redirect:/movies";
		}
        
}