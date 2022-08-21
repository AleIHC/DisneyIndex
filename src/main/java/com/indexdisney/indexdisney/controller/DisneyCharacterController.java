package com.indexdisney.indexdisney.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.indexdisney.indexdisney.models.DisneyCharacter;
import com.indexdisney.indexdisney.models.Movie;
import com.indexdisney.indexdisney.services.DisneyCharacterService;
import com.indexdisney.indexdisney.services.MovieService;



@Controller
@RequestMapping("/characters")
public class DisneyCharacterController {
    
    //Inyección de dependencias
    @Autowired
    DisneyCharacterService disneyCharacterService;

    @Autowired
    MovieService movieService;


        @RequestMapping("")
        public String newDisneyCharacter(@ModelAttribute("character") DisneyCharacter disneyCharacter,Model model) {

            //Lista de películas disponibles
            List<Movie> moviesList = movieService.findAll();

            //Lista de películas o "moviesList" a character.jsp
            model.addAttribute("savedMovies", moviesList);
            return "showcharacters.jsp";
        }

		//Ruta para nuevo personaje
        @RequestMapping("/new")
        public String newDisneyCharacter(@ModelAttribute("character") DisneyCharacter disneyCharacter, 
		BindingResult result, Model model) {

				//Lista de personajes y películas
				List<Movie> moviesList = movieService.findAll();
				List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

				//Lista de personajes a showcharacters.jsp
				model.addAttribute("savedCharacters", disneyCharacterList);
				model.addAttribute("savedMovies", moviesList);
				return "character.jsp";// página a desplegar
		}
        
        /***GUARDAR**/
        //Guardar nuevo personaje
        @PostMapping("/save")
		public String saveDisneyCharacter(@ModelAttribute("character") DisneyCharacter disneyCharacter, 
		BindingResult result, Model model) {

			//Si existe un error
			if (result.hasErrors()) {
				model.addAttribute("msgError", "Ingrese todos los datos");
				return "character.jsp";
			} 
			else {
				//Personaje al service
				disneyCharacterService.saveCharacter(disneyCharacter);

				//Lista de personajes y películas
				List<Movie> moviesList = movieService.findAll();
				List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

				//Lista de personajes a showcharacters.jsp
				model.addAttribute("savedCharacters", disneyCharacterList);
				model.addAttribute("savedMovies", moviesList);
				return "redirect:/characters/show";// página a desplegar
			}
		}


        //Ruta para ver personajes en DB
        @RequestMapping("/show")
		public String showCharacters(Model model) {
        
			//Lista de personajes y de películas
			List<Movie> moviesList = movieService.findAll();
			List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

			//Ambas listas a showcharacters.jsp
			model.addAttribute("savedCharacters", disneyCharacterList);
			model.addAttribute("savedMovies", moviesList);
			return "showcharacters.jsp";
		}


        /***EDICION**/
        //Editar para despliegue de todos los personajes
        @RequestMapping("/edit/{id}")
		public String editCharacter(@PathVariable("id") Long id, Model model) {

            //Buscar por ID en service y redireccionar para la edicion
			DisneyCharacter disneyCharacter = disneyCharacterService.findId(id);
			model.addAttribute("character", disneyCharacter);
			return "editcharacter.jsp";
		}

        //Update que persista en DB
        @PostMapping("/update/{id}")
		public String updateCharacter(@PathVariable("id") Long id ,@Valid @ModelAttribute("character") DisneyCharacter disneyCharacter,
        BindingResult result, Model model) {

			//Si existe un error
			if (result.hasErrors()) {
				model.addAttribute("msgError", "Debe ingresar los datos del personaje correctamente");
				return "editcharacter.jsp";
			} else {
				//Personaje al service
				disneyCharacterService.saveCharacter(disneyCharacter);

				//Lista de personajes
				List<DisneyCharacter> disneyCharacterList = disneyCharacterService.findAll();

				//Lista de personajes a showcharacters.jsp
				model.addAttribute("savedCharacters", disneyCharacterList);
				return "redirect:/characters/show";
			}
		}

        /***ELIMINACIÓN***/
        //Eliminar personaje
		@RequestMapping("/delete/{id}")
		public String deleteCharacter (@PathVariable("id")long id) {

            //Eliminar por id
            disneyCharacterService.deleteById(id);

            //Redireccionar a showcharacters.jsp
            return "redirect:/characters/show";
		}
    
		/***BÚSQUEDA***/
        //Buscar personajes
		@GetMapping("/characters")
		public String searchCharacter(@RequestParam(value="name") String characterName, Model model) {
			if(characterName.equals("")) {
				return "redirect:/characters/show";
			}
			List<DisneyCharacter> characterList = disneyCharacterService.findByName(characterName);
			model.addAttribute("savedCharacters", characterList);
			return "showcharacters.jsp";
		}
        
        
}