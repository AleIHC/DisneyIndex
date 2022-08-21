package com.indexdisney.indexdisney.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.indexdisney.indexdisney.models.DisneyCharacter;
import com.indexdisney.indexdisney.repositories.DisneyCharacterRepository;
import com.indexdisney.indexdisney.repositories.MovieRepository;
import com.indexdisney.indexdisney.services.DisneyCharacterService;
import com.indexdisney.indexdisney.services.MovieService;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/characters")
public class DisneyCharacterRestController {
    
    //Inyección de dependencias
    @Autowired
    DisneyCharacterService disneyCharacterService;

    @Autowired
    MovieService movieService;
    
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DisneyCharacterRepository disneyCharacterRepository;

    
    //Guardar personaje
    @PostMapping 
	public ResponseEntity<DisneyCharacter> saveCharacter(@RequestBody DisneyCharacter disneyCharacter){

        //Guardar 
		DisneyCharacter newDisneyCharacter = disneyCharacterService.saveCharacter(disneyCharacter);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDisneyCharacter.getCharacterId()).toUri();
		return ResponseEntity.created(location).body(newDisneyCharacter);
	}

    //Obtener lista de personajes
    @GetMapping
    public ResponseEntity<List<DisneyCharacter>> disneyCharacterList() {
        return ResponseEntity.ok(disneyCharacterService.findAll());
    }

    //Obtener personaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<DisneyCharacter> getDisneyCharacterById(@PathVariable Long id) {
        Optional<DisneyCharacter> disneyCharacterOptional = disneyCharacterRepository.findById(id);

        if(!disneyCharacterOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(disneyCharacterOptional.get());
    }

    //Obtener personajes por nombre
    @RequestMapping("/characters")
    public ResponseEntity<ArrayList<DisneyCharacter>> getDisneyCharacterByName(@RequestParam(value="name") String name) {
        ArrayList<DisneyCharacter> disneyCharacterOptional = disneyCharacterRepository.findByName(name);

        return ResponseEntity.ok(disneyCharacterOptional);
    }


    //Editar
    @PutMapping("/{id}")
	public ResponseEntity<DisneyCharacter> disneyCharacterUpdate(@Valid @RequestBody DisneyCharacter disneyCharacter, @PathVariable Long id){
		
        //Cambiando personaje e ID
		disneyCharacter.setCharacterId(id);   
		disneyCharacterRepository.save(disneyCharacter);
		return ResponseEntity.ok(disneyCharacter);
	}

    //Borrar
    @DeleteMapping("/{id}")
	public ResponseEntity<DisneyCharacter> deleteDisneyCharacter(@PathVariable Long id){

        //Creando opcional de personaje y verificando si existe en repositorio
		Optional<DisneyCharacter> disneyCharacterOptional = disneyCharacterRepository.findById(id);
		if(!disneyCharacterOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
        //Borrar en repositorio 
		disneyCharacterRepository.delete(disneyCharacterOptional.get());
		return ResponseEntity.noContent().build();
	}
}

