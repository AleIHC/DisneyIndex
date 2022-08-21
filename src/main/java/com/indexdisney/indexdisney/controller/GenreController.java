package com.indexdisney.indexdisney.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.indexdisney.indexdisney.models.Genre;
import com.indexdisney.indexdisney.models.Movie;
import com.indexdisney.indexdisney.services.GenreService;
import com.indexdisney.indexdisney.services.MovieService;

@Controller
@RequestMapping("/genre")
public class GenreController {
    
    //Inyección de dependencias
    @Autowired
    MovieService movieService;

    @Autowired
    GenreService genreService;

        //Mostrar géneros
        @RequestMapping("")
        public String genres(@ModelAttribute("genre") Genre genre,Model model) {

            //Lista de películas disponibles
            List<Genre> genreList = genreService.findAll();
            List<Movie> moviesList = movieService.findAll();

            //Lista de películas y personajes a showmovies.jsp
            model.addAttribute("savedGenre", genreList);
			model.addAttribute("savedMovies", moviesList);
            return "showgenres.jsp";
        }

        

        //Ruta para nuevo género
        @RequestMapping("/new")
        public String newGenre(@ModelAttribute("genre") Genre genre){
            return "newgenre.jsp"; 
        }


         //Guardar género
        @PostMapping("/save")
        public String saveNewGenre(@ModelAttribute("genre") Genre genre, 
        BindingResult result, Model model) {

            if (result.hasErrors()) {
                model.addAttribute("msgError", "Debe ingresar el nuevo género correctamente");
                return "newgenre.jsp";
            } else {
                //Género al service
                genreService.saveGenre(genre);

                //Lista de generos y películas
                List<Movie> moviesList = movieService.findAll();
                List<Genre> genreList = genreService.findAll();

                //Lista de generos y peliculas showmovies.jsp
                model.addAttribute("savedGenres", genreList);
                model.addAttribute("savedMovies", moviesList);
                return "redirect:/genre";
            }
         } 
}