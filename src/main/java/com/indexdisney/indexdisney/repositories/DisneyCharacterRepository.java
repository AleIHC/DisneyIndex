package com.indexdisney.indexdisney.repositories;

import com.indexdisney.indexdisney.models.DisneyCharacter;



import java.util.ArrayList;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisneyCharacterRepository extends JpaRepository<DisneyCharacter, Long> {


    @Query(value="SELECT * from characters WHERE character_name = ?1", nativeQuery = true)
	ArrayList<DisneyCharacter> findByName(String name);
}