package com.indexdisney.indexdisney.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indexdisney.indexdisney.models.DisneyCharacter;
import com.indexdisney.indexdisney.repositories.DisneyCharacterRepository;

@Service
public class DisneyCharacterService {
    
    //Inyección de dependencias
    @Autowired
    DisneyCharacterRepository disneyCharacterRepository;

    //Guardar
    public DisneyCharacter saveCharacter(@Valid DisneyCharacter disneyCharacter) {
		disneyCharacterRepository.save(disneyCharacter);
    return disneyCharacter;
	}

    //Hacer una lista
    public List<DisneyCharacter> findAll() {
		return disneyCharacterRepository.findAll();
	}

    //Buscar por ID
    public DisneyCharacter findId(Long id) {
		return disneyCharacterRepository.findById(id).get();
	}

    //Eliminar por ID
    public void deleteById(long id) {
		disneyCharacterRepository.deleteById(id);
	}

    //Buscar por nombre
    public List<DisneyCharacter> findByName(String characterName) {
	  return disneyCharacterRepository.findByName(characterName);
	}
	

}
